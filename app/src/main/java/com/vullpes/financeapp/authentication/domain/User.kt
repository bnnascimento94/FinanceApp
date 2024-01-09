package com.vullpes.financeapp.authentication.domain

data class User(
    val id: Int = 0,
    val name:String,
    val email: String,
    val password:String,
    val imgSrc:String? = null,
    val active:Boolean = true
)