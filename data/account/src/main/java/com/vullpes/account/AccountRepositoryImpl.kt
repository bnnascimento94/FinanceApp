package com.vullpes.account


import com.vullpes.room.repository.account.AccountRoomDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRoomDataSource: AccountRoomDataSource
): AccountRepository {
    override suspend fun createAccount(account: Account): Account {
       return accountRoomDataSource.createAccount(account)
    }

    override suspend fun updateAccount(account: Account) {
        accountRoomDataSource.updateAccount(account)
    }

    override fun getAccounts(userID: Int): Flow<List<Account>> {
        return accountRoomDataSource.getAccounts(userID)
    }

    override suspend fun findAccountById(accountID: Int): Account? {
        return accountRoomDataSource.findAccountById(accountID)
    }
}