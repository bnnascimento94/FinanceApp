package com.vullpes.financeapp.domain.model

import java.util.Date

data class DayBalance(
    val dayBalanceId:Int,
    val accountID: Int,
    val date: Date,
    val finalBalance:Double
)
