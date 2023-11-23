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
    val accountFromID: Int,
    val accountFromName:String,
    val accountToID: Int? = null,
    val accountToName:String? = null,
    val deposit: Boolean,
    val withdrawal:Boolean,
    val transference:Boolean,
    val value:Double,
    val dateTransaction: Date
)


fun Transaction.toTransactionDb() = TransactionDb(
    transactionID, categoryID, categoryName, accountFromID, accountFromName, accountTo, accountToName, deposit, withdrawal, transference, value, dateTransaction
)

fun TransactionDb.toTransaction() = Transaction(
    transactionID, categoryID, categoryName, accountFromID, accountFromName, accountToID, accountToName, deposit, withdrawal, transference, value, dateTransaction
)