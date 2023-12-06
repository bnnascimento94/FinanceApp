package com.vullpes.financeapp.presentation.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.financeapp.domain.usecases.authentication.LoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsecase: LoginUsecase
): ViewModel() {
    var uiState by mutableStateOf(UiStateLogin())
        private set

    fun setUsername(username: String){
        uiState = uiState.copy(user = username)
        checkEnableLoginButton()
    }

    fun setPassword(password:String){
        uiState = uiState.copy(password = password)
        checkEnableLoginButton()
    }

    fun save(onSuccess:() -> Unit, onError:(String) -> Unit) = viewModelScope.launch(Dispatchers.IO){
        try{
            val result =  loginUsecase.execute(username = uiState.user, password = uiState.password)
            withContext(Dispatchers.Main){
                if(result){
                    onSuccess()
                }else{
                    onError("Usuário Não Encontrado")
                }
            }
        }catch (e:Exception){
            e.message?.let { onError(it) }
        }
    }

    private fun checkEnableLoginButton(){
        uiState = uiState.copy(
            loginButtonEnabled = uiState.user.isNotBlank() && uiState.password.isNotBlank()
        )
    }
}