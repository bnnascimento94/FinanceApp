package com.vullpes.financeapp.presentation.authentication.register

data class UiStateRegister(
    val user:String = "",
    val password:String = "",
    val confirmPassword:String = "",
    val loginButtonEnabled:Boolean = false,
    val loading:Boolean = false
)