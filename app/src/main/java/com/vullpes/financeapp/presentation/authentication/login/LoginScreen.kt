package com.vullpes.financeapp.presentation.authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vullpes.financeapp.R

@Composable
fun LoginScreen(
    uiStateLogin: UiStateLogin,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSignInClicked: () -> Unit,
    onForgotPassword:() -> Unit,
    onRegisterUser:() -> Unit,

) {
    Box(modifier = Modifier
        .background(color = Color.Blue.copy(alpha = 0.5f))
        .fillMaxSize()

    ) {

        Image(painter = painterResource(R.drawable.finance_money),
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp)
                .clip(shape = CircleShape)
                .align(Alignment.TopCenter),

            contentDescription = "Image Jui")
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center)

        ) {

            var passwordVisibility = remember{ mutableStateOf(false) }

            OutlinedTextField(
                value = uiStateLogin.user,
                label = { Text(text = stringResource(R.string.user), color = Color.White) },
                onValueChange = onUsernameChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 30.dp, bottom = 8.dp),
                leadingIcon = {
                    Icon(Icons.Filled.Store, contentDescription = stringResource(R.string.password_icon), tint = Color.White )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )

            )

            OutlinedTextField(
                value =uiStateLogin.password,
                label = { Text(text = stringResource(R.string.password), color = Color.White) },
                onValueChange = onPasswordChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                visualTransformation = if(passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(Icons.Filled.Key, contentDescription =stringResource(R.string.password_icon), tint = Color.White )
                },
                trailingIcon = {
                    IconButton(onClick = {passwordVisibility.value = !passwordVisibility.value}){
                        Icon(
                            imageVector = if(passwordVisibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = stringResource(R.string.show_password),
                            tint = Color.White
                        )
                    }
                },

                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )





            Spacer(modifier = Modifier.height(10.dp))

            Button(enabled = true,
                onClick = { onSignInClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
            {
                if(uiStateLogin.loading){
                    CircularProgressIndicator()
                }else{
                    Text(text = stringResource(R.string.login),fontFamily = FontFamily.Default, color = Color.White)
                }
            }



            ClickableText(
                text = AnnotatedString(stringResource(R.string.forgot_password)),
                onClick = {
                    onForgotPassword()
                          },
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    color = Color.White
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(12.dp)
            )
        }

        ClickableText(
            text = AnnotatedString(stringResource(R.string.register_button)),
            onClick = {
                onRegisterUser()
                },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrevLogin() {
    LoginScreen(
        uiStateLogin = UiStateLogin(),
        onUsernameChanged = {},
        onPasswordChanged = {},
        onSignInClicked = { /*TODO*/ },
        onRegisterUser = {},
        onForgotPassword = {}
        )
}

