package com.vullpes.room.repository.account

import com.vullpes.financeapp.account.domain.Account
import kotlinx.coroutines.flow.Flow

interface AccountRoomDataSource {
    suspend fun createAccount(account: Account): Account
    suspend fun updateAccount(account: Account)
    fun getAccounts(userID: Int): Flow<List<Account>>

    suspend fun findAccountById(accountID: Int): Account?

}