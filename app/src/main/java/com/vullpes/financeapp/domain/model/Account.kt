package com.vullpes.financeapp.domain.model

import java.util.Date

data class Account(
    val accountID: Int = 0,
    val accountName:String? = null,
    val accountBalance:Double? = null,
    val dataCreationAccount: Date = Date(),
    val activeAccount:Boolean = true
)
