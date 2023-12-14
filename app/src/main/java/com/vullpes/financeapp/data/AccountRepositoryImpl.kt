package com.vullpes.financeapp.data

import com.vullpes.financeapp.data.dataSource.room.repository.account.AccountRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSource
import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRoomDataSource: AccountRoomDataSource
): AccountRepository {
    override suspend fun createAccount(account: Account):Account {
       return accountRoomDataSource.createAccount(account)
    }

    override suspend fun updateAccount(account: Account) {
        accountRoomDataSource.updateAccount(account)
    }

    override fun getAccounts(): Flow<List<Account>> {
        return accountRoomDataSource.getAccounts()
    }

    override suspend fun findAccountById(accountID: Int): Account? {
        return accountRoomDataSource.findAccountById(accountID)
    }
}