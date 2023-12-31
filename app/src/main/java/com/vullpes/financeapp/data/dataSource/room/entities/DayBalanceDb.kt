package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vullpes.financeapp.domain.model.DayBalance
import java.util.Date

@Entity
data class DayBalanceDb (
    @PrimaryKey(autoGenerate = true)
    val dayBalanceId:Int,
    val accountID: Int,
    val date: Date,
    val finalBalance:Double

)

fun DayBalanceDb.toDayBalance() = DayBalance(
    dayBalanceId, accountID, date, finalBalance
)

fun DayBalance.toDayBalanceDb() = DayBalanceDb(
    dayBalanceId, accountID, date, finalBalance
)