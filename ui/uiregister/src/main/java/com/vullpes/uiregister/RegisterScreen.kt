package com.vullpes.uiregister

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.common.R

@Composable
fun RegisterScreen(
    uiState: UiStateRegister,
    onBackPressed: () -> Unit,
    onUsernameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPassword:(String) -> Unit,
    onRegisterClicked: () -> Unit,

    ) {
    BackHandler {
        onBackPressed()
    }
    Scaffold() {padding ->
        Box(
            modifier = Modifier
                .fillMaxSize().padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .align(Alignment.Center)

            ) {

                var passwordVisibility = remember { mutableStateOf(false) }
                var confirmPasswordVisibility = remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = uiState.user,
                    label = { Text(text = stringResource(R.string.user)) },
                    onValueChange = onUsernameChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = stringResource(R.string.password_icon)
                        )
                    }
                )

                OutlinedTextField(
                    value = uiState.email,
                    label = { Text(text = "E-mail") },
                    onValueChange = onEmailChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = stringResource(R.string.password_icon)
                        )
                    }
                )

                OutlinedTextField(
                    value = uiState.password,
                    label = { Text(text = stringResource(R.string.password)) },
                    onValueChange = onPasswordChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Key,
                            contentDescription = stringResource(R.string.password_icon)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                imageVector = if (passwordVisibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(R.string.show_password),
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = uiState.confirmPassword,
                    label = { Text(text = stringResource(R.string.confirm_password)) },
                    onValueChange = onConfirmPassword,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Key,
                            contentDescription = stringResource(R.string.password_icon)
                        )
                    },
                    supportingText = {
                        if (uiState.passwordsDoesntMatch) {
                            Text(text = stringResource(R.string.passwords_doesn_t_match))
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                        }) {
                            Icon(
                                imageVector = if (confirmPasswordVisibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(R.string.show_password),
                            )
                        }
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    enabled = uiState.loginButtonEnabled,
                    onClick = { onRegisterClicked() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                )
                {
                    Text(
                        text = stringResource(R.string.register),
                        fontFamily = FontFamily.Default,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevRegister() {
    RegisterScreen(uiState = UiStateRegister(),
        {},{},{},{},{},{}
    )
}