package com.vullpes.financeapp.presentation.authentication.login

import com.vullpes.financeapp.domain.usecases.authentication.AllowBiometricsUsecase

data class UiStateLogin(
    val email:String = "",
    val password:String = "",
    val loginButtonEnabled:Boolean = false,
    val allowBiometrics:Boolean = false,
    val loading:Boolean = false
)
