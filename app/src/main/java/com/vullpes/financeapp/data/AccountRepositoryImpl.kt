package com.vullpes.financeapp.data

import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.model.Account
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl: AccountRepository {
    override suspend fun createAccount(account: Account) {
        TODO("Not yet implemented")
    }

    override suspend fun updateAccount(account: Account) {
        TODO("Not yet implemented")
    }

    override fun getAccounts(): Flow<Account> {
        TODO("Not yet implemented")
    }
}