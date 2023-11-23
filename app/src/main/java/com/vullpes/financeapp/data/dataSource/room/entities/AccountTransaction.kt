package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.vullpes.financeapp.domain.model.Transaction

data class AccountTransaction(
    @Embedded
    val transactionDb: TransactionDb,

    @Relation(
        parentColumn = "accountFromID",
        entityColumn = "accountID"
    )
    val accountFromDb: AccountDb,

    @Relation(
        parentColumn = "accountToID",
        entityColumn = "accountID"
    )
    val accountToDb: AccountDb?,

    @Relation(
        parentColumn = "categoryID",
        entityColumn = "categoryID"
    )
    val categoryDb: CategoryDb

)

fun AccountTransaction.toTransaction() = Transaction(
    transactionID = transactionDb.transactionID,
    categoryID = categoryDb.categoryID,
    categoryName = categoryDb.nameCategory,
    accountFromID = accountFromDb.accountID,
    accountFromName = accountFromDb.accountName,
    accountTo = accountToDb?.accountID,
    accountToName = accountToDb?.accountName,
    deposit = transactionDb.deposit,
    withdrawal = transactionDb.withdrawal,
    transference = transactionDb.transference ,
    value = transactionDb.value,
    dateTransaction = transactionDb.dateTransaction
)