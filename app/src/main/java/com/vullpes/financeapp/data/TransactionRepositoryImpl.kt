package com.vullpes.financeapp.data

import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSource
import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionRoomDataSource: TransactionRoomDataSource) : TransactionRepository {
    override suspend fun createTransaction(transaction: Transaction) {
        transactionRoomDataSource.createTransaction(transaction)
    }

    override fun listTransactions(accountID: Int,date: Date): Flow<List<Transaction>> {
        return transactionRoomDataSource.listTransactions(accountID = accountID,date = date)
    }

    override suspend fun deleteTransaction(transactionID: Int) {
        return transactionRoomDataSource.deleteTransaction(transactionID)
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        transactionRoomDataSource.updateTransaction(transaction)
    }

    override  fun getLastTransactionsByAccount(accountID: Int): Flow<List<Transaction>> {
       return transactionRoomDataSource.getLastTransactionsByAccount(accountID)
    }
}