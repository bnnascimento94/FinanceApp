package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class DayBalanceDb (
    @PrimaryKey
    val dayBalanceId:Int,
    val accountID: Int,
    val date: Date,
    val finalBalance:Double

)