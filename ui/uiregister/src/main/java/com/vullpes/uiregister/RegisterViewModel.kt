package com.vullpes.uiregister

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.authentication.usecases.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
    var uiState by mutableStateOf(UiStateRegister())
        private set

    init {

    }

    fun setUsername(username: String) {
        uiState = uiState.copy(user = username)
        checkEnableRegisterButton()
    }



    fun email(email: String) {
        uiState = uiState.copy(email = email)
        checkEnableRegisterButton()
    }

    fun setPassword(password: String) {
        uiState = uiState.copy(password = password)
        checkEnableRegisterButton()
    }

    fun confirmPassword(confirmPassword: String) {
        uiState = uiState.copy(confirmPassword = confirmPassword)
        if(uiState.password != confirmPassword){
            uiState = uiState.copy(passwordsDoesntMatch = true)
        }else{
            uiState = uiState.copy(passwordsDoesntMatch = false)
        }
        checkEnableRegisterButton()
    }

    private fun checkEnableRegisterButton() {
        uiState = uiState.copy(
            loginButtonEnabled = uiState.user.isNotBlank() &&
                    uiState.password.isNotBlank() &&
                    uiState.confirmPassword.isNotBlank() &&
                    uiState.password == uiState.confirmPassword
        )
    }

    fun save(onSuccess: () -> Unit, onError: (String) -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        try {
            createUserUseCase.execute(
                username = uiState.user,
                email = uiState.email,
                password = uiState.password
            )
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main){
                e.message?.let { onError(it) }
            }
        }
    }
}