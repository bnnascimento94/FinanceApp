package com.vullpes.room.repository.account

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.entities.toAccount
import com.vullpes.financeapp.data.dataSource.room.entities.toAccountDb
import com.vullpes.financeapp.account.domain.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase):
    AccountRoomDataSource {
    override suspend fun createAccount(account: Account) : Account {
        try {
             val accountDao = financeAppDatabase.accountDao()
             val accountID = accountDao.insert(account.toAccountDb())
             return accountDao.findAccountById(accountID.toInt()).toAccount()
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun updateAccount(account: Account) {
        try{
            val accountDao = financeAppDatabase.accountDao()
            accountDao.update(account.toAccountDb())
        }catch (e:Exception){
            throw e
        }
    }

    override fun getAccounts(userID: Int): Flow<List<Account>> {
        try {
            val accountDao = financeAppDatabase.accountDao()
            return accountDao.getAccountDb(userID).map { accountList ->
                accountList.map { account -> account.toAccount() }
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun findAccountById(accountID: Int): Account? {
        try {
            val accountDao = financeAppDatabase.accountDao()
            return accountDao.findAccountById(accountID).toAccount()
        }catch (e:Exception){
            throw e
        }
    }
}