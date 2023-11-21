package com.vullpes.financeapp.data.dataSource.room.repository.transaction

import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.util.Months
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

class TransactionRoomDataSourceImpl(): TransactionRoomDataSource {
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