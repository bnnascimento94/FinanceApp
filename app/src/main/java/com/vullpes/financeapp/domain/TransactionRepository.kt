package com.vullpes.financeapp.domain

import com.vullpes.financeapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRepository {

    suspend fun createTransaction(transaction: Transaction)
    fun listTransactions(date:Date): Flow<List<Transaction>>
    suspend fun deleteTransaction(transactrionID: Int)
    suspend fun updateTransaction(transaction: Transaction)
}