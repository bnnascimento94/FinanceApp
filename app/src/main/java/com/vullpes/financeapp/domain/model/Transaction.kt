package com.vullpes.financeapp.domain.model

import java.util.Date

data class Transaction(
    val transactionID: Int,
    val categoryID:Int,
    val categoryName:String,
    val accountID: Int,
    val accountName:String,
    val dateTransaction: Date
)
