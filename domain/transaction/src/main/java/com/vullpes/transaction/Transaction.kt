package com.vullpes.transaction

import java.util.Date

data class Transaction(
    val transactionID: Int = 0,
    val name:String = "",
    val categoryID:Int? = 0,
    val categoryName:String? = "",
    val accountFromID: Int = 0,
    val accountFromName:String = "",
    val accountTo: Int? = null,
    val accountToName:String? = null,
    val deposit: Boolean = false,
    val withdrawal:Boolean = false,
    val transference:Boolean = false,
    val value:Double = 0.0,
    val dateTransaction: Date = Date()
)
