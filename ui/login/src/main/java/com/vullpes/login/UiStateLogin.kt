package com.vullpes.login

data class UiStateLogin(
    val email:String = "",
    val password:String = "",
    val loginButtonEnabled:Boolean = false,
    val allowBiometrics:Boolean = false,
    val loading:Boolean = false
)
