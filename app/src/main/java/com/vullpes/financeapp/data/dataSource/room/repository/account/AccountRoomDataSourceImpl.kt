package com.vullpes.financeapp.data.dataSource.room.repository.account

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.domain.model.Account
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase): AccountRoomDataSource {
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