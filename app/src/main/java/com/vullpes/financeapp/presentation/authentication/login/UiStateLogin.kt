package com.vullpes.financeapp.presentation.authentication.login

data class UiStateLogin(
    val email:String = "",
    val password:String = "",
    val loginButtonEnabled:Boolean = false,
    val loading:Boolean = false
)
