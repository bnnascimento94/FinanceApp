package com.vullpes.profile

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.vullpes.profile.components.ProfileTopAppBar


@Composable
fun ProfileScreen(
    uiStateProfile: UiStateProfile,
    onBackScreen: () -> Unit,
    onEditImage: () -> Unit,
    onSave:() -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onChangeBiometricAuth:(Boolean) ->Unit
) {

    val passwordVisibility = remember{ mutableStateOf(false) }
    val context = LocalContext.current

    BackHandler {
        onBackScreen()
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val permissions = arrayOf(
        Manifest.permission.USE_BIOMETRIC
    )


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ProfileTopAppBar(onBackScreen = onBackScreen)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                uiStateProfile.profile?.imgSrc?.let {
                    AsyncImage(
                        model = uiStateProfile.profile.imgSrc,
                        placeholder = painterResource(R.drawable.no_user),
                        modifier = Modifier
                            .padding(6.dp)
                            .clip(CircleShape)
                            .size(100.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(R.string.profile_image)
                    )
                }?: kotlin.run {
                    Image(
                        painterResource(R.drawable.no_user),
                        modifier = Modifier
                            .padding(6.dp)
                            .clip(CircleShape)
                            .size(100.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = stringResource(R.string.profile_image)
                    )
                }


                OutlinedButton(onClick = onEditImage) {
                    Text(text = "Edit Image")
                }
                Spacer(modifier = Modifier.height(60.dp))


                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text("Name") },
                    value = uiStateProfile.profile?.name?:"",
                    onValueChange = onNameChanged,
                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text("Email Address") },
                    value = uiStateProfile.profile?.email?:"",
                    onValueChange = onEmailChanged,
                )

                OutlinedTextField(
                    value = uiStateProfile.profile?.password?:"",
                    label = { Text(text = "Password")},
                    readOnly = true,
                    onValueChange = onPasswordChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    visualTransformation = if(passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {passwordVisibility.value = !passwordVisibility.value}){
                            Icon(
                                imageVector = if(passwordVisibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(R.string.show_password),
                            )
                        }
                    },
                )

                if(uiStateProfile.checkBiometricSupport){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                        Text(stringResource(R.string.allow_biometric_auth), style = MaterialTheme.typography.titleMedium)
                        Switch(
                            checked = uiStateProfile.biometricAuthpermission,
                            onCheckedChange = { checkedValue ->
                                if (permissions.all { permission ->
                                        ContextCompat.checkSelfPermission(
                                            context,
                                            permission
                                        ) == PackageManager.PERMISSION_GRANTED
                                    }) {
                                    onChangeBiometricAuth(checkedValue)
                                } else {
                                    launcherMultiplePermissions.launch(permissions)
                                }

                            }
                        )
                    }
                }

            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),onClick = { onSave()}) {
                Text(text = stringResource(R.string.save))
            }
        }



    }
}


@Preview
@Composable
fun PreviewProfile() {
    ProfileScreen(UiStateProfile(),onBackScreen = {  }, onEditImage = {}, onSave = {}, {},{},{},{})
}