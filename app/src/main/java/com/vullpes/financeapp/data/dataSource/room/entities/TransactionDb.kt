package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vullpes.financeapp.domain.model.Transaction
import java.util.Date

@Entity
data class TransactionDb(
    @PrimaryKey
    val transactionID: Int,
    val categoryID:Int,
    val categoryName:String,
    val accountID: Int,
    val accountName:String,
    val deposit: Boolean,
    val withdrawal:Boolean,
    val value:Double,
    val dateTransaction: Date
)


fun Transaction.toTransactionDb() = TransactionDb(
    transactionID, categoryID, categoryName, accountID, accountName, deposit, withdrawal, value, dateTransaction
)