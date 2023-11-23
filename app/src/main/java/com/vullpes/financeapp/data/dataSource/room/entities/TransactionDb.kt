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
    val accountTo: Int? = null,
    val accountToName:String? = null,
    val deposit: Boolean,
    val withdrawal:Boolean,
    val transference:Boolean,
    val value:Double,
    val dateTransaction: Date
)


fun Transaction.toTransactionDb() = TransactionDb(
    transactionID, categoryID, categoryName, accountID, accountName, accountTo, accountToName, deposit, withdrawal, transference, value, dateTransaction
)

fun TransactionDb.toTransaction() = Transaction(
    transactionID, categoryID, categoryName, accountID, accountName, accountTo, accountToName, deposit, withdrawal, transference, value, dateTransaction
)