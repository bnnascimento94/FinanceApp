package com.vullpes.financeapp.domain.model

import java.util.Date

data class Transaction(
    val transactionID: Int,
    val categoryID:Int,
    val categoryName:String,
    val accountFromID: Int,
    val accountFromName:String,
    val accountTo: Int? = null,
    val accountToName:String? = null,
    val deposit: Boolean,
    val withdrawal:Boolean,
    val transference:Boolean,
    val value:Double,
    val dateTransaction: Date
)
