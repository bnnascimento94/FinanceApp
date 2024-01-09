package com.vullpes.financeapp.transaction.data

import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSource
import com.vullpes.financeapp.transaction.domain.TransactionRepository
import com.vullpes.financeapp.account.domain.Account
import com.vullpes.financeapp.charts.domain.DayBalance
import com.vullpes.financeapp.transaction.domain.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionRoomDataSource: TransactionRoomDataSource
) : TransactionRepository {


    override suspend fun createTransaction(
        transaction: Transaction,
        account: Account,
        accountTo: Account?,
        transactionTransference: Transaction?,
        dayBalance: DayBalance,
        dayBalanceAccountTo: DayBalance?
    ) {
        transactionRoomDataSource.createTransaction(
            transaction,
            account,
            accountTo,
            transactionTransference,
            dayBalance,
            dayBalanceAccountTo
        )
    }

    override fun listTransactions(accountID: Int, date: Date): Flow<List<Transaction>> {
        return transactionRoomDataSource.listTransactions(accountID = accountID, date = date)
    }



    override suspend fun findTransactionById(transactionID: Int): Transaction? {
        return transactionRoomDataSource.findTransactionById(transactionID)
    }

    override fun getLastTransactionsByAccount(accountID: Int): Flow<List<Transaction>> {
        return transactionRoomDataSource.getLastTransactionsByAccount(accountID)
    }

    override fun getAllTransactionsByAccount(accountID: Int): Flow<List<Transaction>> {
        return transactionRoomDataSource.listAllTransactionsByAccount(accountID)
    }

    override fun listAllTransactionsByAccountName(transactionName: String,accountID: Int): Flow<List<Transaction>> {
        return transactionRoomDataSource.listAllTransactionsByAccountName(transactionName, accountID)
    }

    override suspend fun listTransactionsByAccountAndDate(
        accountID: Int,
        date1: Date,
        date2: Date
    ): List<Transaction> {
        return transactionRoomDataSource.listTransactionsByAccountAndDate(accountID, date1, date2)
    }


}