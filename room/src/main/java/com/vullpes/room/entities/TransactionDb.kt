package com.vullpes.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vullpes.financeapp.transaction.domain.Transaction
import java.util.Date

@Entity
data class TransactionDb(
    @PrimaryKey(autoGenerate = true)
    val transactionID: Int,
    val name:String,
    val categoryID:Int? = null,
    val categoryName:String? = null,
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
    transactionID, name,categoryID, categoryName, accountFromID, accountFromName, accountTo, accountToName, deposit, withdrawal, transference, value, dateTransaction
)

fun TransactionDb.toTransaction() = Transaction(
    transactionID,name, categoryID?: 0, categoryName?:"", accountFromID, accountFromName, accountToID, accountToName, deposit, withdrawal, transference, value, dateTransaction
)