package com.vullpes.financeapp.presentation.authentication.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.financeapp.R
import com.vullpes.financeapp.domain.usecases.authentication.AllowBiometricsUsecase
import com.vullpes.financeapp.domain.usecases.authentication.BiometricAuthenticationUsecase
import com.vullpes.financeapp.domain.usecases.authentication.ForgotPasswordUsecase
import com.vullpes.financeapp.domain.usecases.authentication.GetBiometricStatusUsecase
import com.vullpes.financeapp.domain.usecases.authentication.LoginUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val loginUsecase: LoginUsecase,
    private val forgotPasswordUsecase: ForgotPasswordUsecase,
    private val getBiometricStatusUsecase: GetBiometricStatusUsecase,
    private val biometricAuthenticationUsecase: BiometricAuthenticationUsecase
): ViewModel() {

    var uiState by mutableStateOf(UiStateLogin())
        private set
    init {
        uiState = uiState.copy(allowBiometrics = getBiometricStatusUsecase.execute())
    }



    fun biometricAuth(onSuccess: () -> Unit, onError: (String) -> Unit){
        biometricAuthenticationUsecase.execute(onSuccess, onError)
    }

    fun setUsername(username: String){
        uiState = uiState.copy(email = username)
        checkEnableLoginButton()
    }

    fun setPassword(password:String){
        uiState = uiState.copy(password = password)
        checkEnableLoginButton()
    }

    fun save(onSuccess:() -> Unit, onError:(String) -> Unit) = viewModelScope.launch(Dispatchers.IO){
        try{
            val result =  loginUsecase.execute(username = uiState.email, password = uiState.password)
            withContext(Dispatchers.Main){
                if(result){
                    onSuccess()
                }else{
                    onError(context.getString(R.string.user_not_found))
                }
            }
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                e.message?.let { onError(it) }
            }
        }
    }

    private fun checkEnableLoginButton(){
        uiState = uiState.copy(
            loginButtonEnabled = uiState.email.isNotBlank() && uiState.password.isNotBlank()
        )
    }
    
    fun forgotPassword(onSuccess: (String) -> Unit, onError: (String) -> Unit) = viewModelScope.launch(Dispatchers.IO){
        try {
            if(uiState.email.isNotBlank()){
                val result = forgotPasswordUsecase.execute(uiState.email)
                withContext(Dispatchers.Main) {
                    if(result){
                        onSuccess(context.getString(R.string.an_e_mail_has_sent_with_your_new_credentials))
                    }else{
                        onError(context.getString(R.string.the_e_mail_supplied_has_not_been_found))
                    }
                }
            }else{
                withContext(Dispatchers.Main){
                    onError(context.getString(R.string.e_mail_not_supplied))
                }
            }
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                onError(context.getString(R.string.e_mail_not_supplied))
            }
        }

    }
}