package com.vullpes.financeapp.domain

import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow


interface AccountRepository {
    suspend fun createAccount(account: Account)
    suspend fun updateAccount(account: Account)
    fun getAccounts(): Flow<List<Account>>

    suspend fun findAccountById(accountID: Int): Account?
}