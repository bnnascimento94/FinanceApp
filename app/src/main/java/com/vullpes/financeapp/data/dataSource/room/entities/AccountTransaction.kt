package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.vullpes.financeapp.domain.model.Transaction

data class AccountTransaction(
    @Embedded
    val transactionDb: TransactionDb,

    @Relation(
        parentColumn = "accountID",
        entityColumn = "accountID"
    )
    val accountDb: AccountDb,

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
    accountFromID = accountDb.accountID,
    accountFromName = accountDb.accountName,
    deposit = transactionDb.deposit,
    withdrawal = transactionDb.withdrawal,
    value = transactionDb.value,
    dateTransaction = transactionDb.dateTransaction
)