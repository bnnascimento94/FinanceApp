package com.vullpes.financeapp.data.dataSource.room.repository.account

import com.vullpes.financeapp.domain.model.Account
import kotlinx.coroutines.flow.Flow

class AccountRoomDataSourceImpl(): AccountRoomDataSource {
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