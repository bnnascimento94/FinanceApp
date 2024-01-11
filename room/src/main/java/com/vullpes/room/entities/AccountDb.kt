package com.vullpes.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vullpes.account.Account
import java.util.Date

@Entity
data class AccountDb(
    @PrimaryKey(autoGenerate = true)
    val accountID: Int,
    val accountName:String,
    val accountBalance:Double,
    val dataCreationAccount: Date,
    val activeAccount:Boolean,
    val userID: Int
)


fun AccountDb.toAccount() = Account(
    accountID, accountName, accountBalance, dataCreationAccount, activeAccount, userID
)

fun Account.toAccountDb() = AccountDb(
    accountID, accountName, accountBalance, dataCreationAccount, activeAccount, userID
)
