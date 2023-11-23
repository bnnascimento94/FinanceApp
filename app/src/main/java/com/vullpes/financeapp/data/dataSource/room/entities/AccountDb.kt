package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vullpes.financeapp.domain.model.Account
import java.util.Date

@Entity
data class AccountDb(
    @PrimaryKey
    val accountID: Int,
    val accountName:String,
    val accountBalance:Double,
    val dataCreationAccount: Date,
    val activeAccount:Boolean
)


fun AccountDb.toAccount() = Account(
    accountID, accountName, accountBalance, dataCreationAccount, activeAccount
)

fun Account.toAccountDb() = AccountDb(
    accountID, accountName, accountBalance, dataCreationAccount, activeAccount
)
