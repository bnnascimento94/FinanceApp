package com.vullpes.financeapp.account.domain

import java.util.Date

data class Account(
    val accountID: Int = 0,
    val accountName:String = "",
    val accountBalance:Double = 0.0,
    val dataCreationAccount: Date = Date(),
    val activeAccount:Boolean = true,
    var userID: Int = 0
)
