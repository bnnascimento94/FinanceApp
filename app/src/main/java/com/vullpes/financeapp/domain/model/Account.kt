package com.vullpes.financeapp.domain.model

import java.util.Date

data class Account(
    val accountID: Int,
    val accountName:String,
    val accountBalance:Double,
    val dataCreationAccount: Date,
    val activeAccount:Boolean
)
