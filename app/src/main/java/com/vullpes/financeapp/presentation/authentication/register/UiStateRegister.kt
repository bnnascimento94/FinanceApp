package com.vullpes.financeapp.presentation.authentication.register

data class UiStateRegister(
    val user:String = "",
    val email:String = "",
    val password:String = "",
    val confirmPassword:String = "",
    val passwordsDoesntMatch:Boolean = false,
    val loginButtonEnabled:Boolean = false,
    val loading:Boolean = false
)