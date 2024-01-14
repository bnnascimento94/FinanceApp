package com.vullpes.profile

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vullpes.common.R
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.profileRoute(
    onBackPressed: () -> Unit,
    onInteraction: () -> Unit
) {
    composable(route = com.vullpes.common.navigation.Screen.Profile.route) {
        val viewModel: ProfileViewModel = hiltViewModel()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        ProfileScreen(
            uiStateProfile = viewModel.uiState,
            onBackScreen = onBackPressed,
            onEditImage = {
                viewModel.dialogImageOpen()
                onInteraction()
            },
            onSave = {
                viewModel.onSave(onSuccess = {
                    Toast.makeText(context,
                        context.getString(R.string.user_updated), Toast.LENGTH_SHORT).show()
                }, onError = {message -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show()})
                onInteraction()
            },
            onNameChanged = {
                viewModel.setName(it)
                onInteraction()
            },
            onEmailChanged = {
                viewModel.setEmail(it)
                onInteraction()
            },
            onPasswordChanged = {
                viewModel.setPassword(it)
                onInteraction()
            },
            onChangeBiometricAuth = {
                viewModel.biometricAllowance(it)
            }
        )

        if (viewModel.uiState.openImageDialog) {
            com.vullpes.components.ModalBottomSheetChangePicture(
                onDismiss = {
                    viewModel.dialogImageOpen()
                    onInteraction()
                },
                onAddImageFromGallery = { sheetStatus, uri ->
                    scope.launch {
                        sheetStatus.hide()
                    }
                    viewModel.setImageBitmap(uri)
                    onInteraction()
                },
                onAddImage = {
                    scope.launch {
                        it.hide()
                    }
                    viewModel.setImageBitmap()
                    onInteraction()
                },
                createUri = {
                    viewModel.createImageUri()
                }
            )
        }

        if(viewModel.uiState.loading){
            com.vullpes.components.LoadingDialog {}
        }

    }
}