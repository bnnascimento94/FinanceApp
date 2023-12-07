package com.vullpes.financeapp.data.dataSource.room.repository.transaction

import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.util.Months
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRoomDataSource {

    suspend fun createTransaction(transaction: Transaction)
    fun listTransactions(accountID:Int,date: Date): Flow<List<Transaction>>

    fun getLastTransactionsByAccount(accountID:Int): Flow<List<Transaction>>
    suspend fun deleteTransaction(transactionID: Int)
    suspend fun updateTransaction(transaction: Transaction)

}