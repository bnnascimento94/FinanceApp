package com.vullpes.account

import kotlinx.coroutines.flow.Flow


interface AccountRepository {
    suspend fun createAccount(account: Account): Account
    suspend fun updateAccount(account: Account)
    fun getAccounts(userID: Int): Flow<List<Account>>

    suspend fun findAccountById(accountID: Int): Account?
}