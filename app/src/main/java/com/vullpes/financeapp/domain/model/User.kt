package com.vullpes.financeapp.domain.model

data class User(
    val id: Int,
    val name:String,
    val email: String,
    val password:String,
    val imgSrc:String
)