package com.vullpes.financeapp.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vullpes.financeapp.R
import com.vullpes.financeapp.presentation.profile.components.ProfileTopAppBar


@Composable
fun ProfileScreen(
    uiStateProfile: UiStateProfile,
    onBackScreen: () -> Unit,
    onEditImage: () -> Unit,
    onSave:() -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {

    val passwordVisibility = remember{ mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ProfileTopAppBar(onBackScreen = onBackScreen)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = uiStateProfile.profile?.imgSrc,
                    placeholder = painterResource(R.drawable.no_user),
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(CircleShape)
                        .size(100.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.profile_image)
                )

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
            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),onClick = { onSave()}) {
                Text(text = "Save")
            }
        }



    }
}


@Preview
@Composable
fun PreviewProfile() {
    ProfileScreen(UiStateProfile(),onBackScreen = {  }, onEditImage = {}, onSave = {}, {},{},{})
}