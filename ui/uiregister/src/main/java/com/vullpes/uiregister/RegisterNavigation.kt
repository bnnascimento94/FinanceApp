package com.vullpes.uiregister

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vullpes.common.navigation.Screen

fun NavGraphBuilder.registerRoute(
    onBackPressed: () -> Unit,
    onSignIn: () -> Unit
) {
    composable(route = com.vullpes.common.navigation.Screen.Register.route) {
        val viewModel: RegisterViewModel = hiltViewModel()
        val context = LocalContext.current

        RegisterScreen(
            uiState = viewModel.uiState,
            onBackPressed = onBackPressed,
            onUsernameChanged = {
                viewModel.setUsername(it)
            },
            onEmailChanged = {
                viewModel.email(it)
            },
            onPasswordChanged = {
                viewModel.setPassword(it)
            },
            onConfirmPassword = {
                viewModel.confirmPassword(it)
            },
            onRegisterClicked = {
                viewModel.save(
                    onSuccess = {
                        onSignIn()
                    },
                    onError = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        )
    }
}