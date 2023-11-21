package com.vullpes.financeapp.data.dataSource.room.repository.account

import com.vullpes.financeapp.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRoomDataSource {
    suspend fun createAccount(account: Account)
    suspend fun updateAccount(account: Account)
    fun getAccounts(): Flow<Account>
}