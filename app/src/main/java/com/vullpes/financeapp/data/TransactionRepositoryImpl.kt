package com.vullpes.financeapp.data

import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSource
import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(transactionRoomDataSource: TransactionRoomDataSource) : TransactionRepository {
    override suspend fun createTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun listTransactions(date: Date): Flow<List<Transaction>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTransaction(transactrionID: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }
}