package com.vullpes.login

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vullpes.common.navigation.Screen

fun NavGraphBuilder.loginRoute(
    onSignIn: () -> Unit,
    onRegisterUser:() -> Unit
) {

    composable(route = com.vullpes.common.navigation.Screen.Login.route) {
        val viewModel: LoginViewModel = hiltViewModel()
        val context = LocalContext.current

        LoginScreen(
            uiStateLogin = viewModel.uiState,
            onUsernameChanged = {
                viewModel.setUsername(it)
            },
            onPasswordChanged = {
                viewModel.setPassword(it)
            },
            onSignInClicked = {
                viewModel.save(
                    onSuccess = {
                        onSignIn()
                    },
                    onError = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            onForgotPassword = {
                viewModel.forgotPassword(
                    onSuccess = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    },
                    onError = {error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            onRegisterUser = onRegisterUser,
            useBiometrics = {
                viewModel.biometricAuth(
                    onSuccess = {
                        onSignIn()
                    },
                    onError = {
                        Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
                    })
            }
        )
    }
}