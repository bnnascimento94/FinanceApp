package com.vullpes.financeapp.domain

import com.vullpes.financeapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRepository {

    suspend fun createTransaction(transaction: Transaction)
    fun listTransactions(accountID: Int,date: Date): Flow<List<Transaction>>
    suspend fun deleteTransaction(transactionID: Int)
    suspend fun updateTransaction(transaction: Transaction)
    fun getLastTransactionsByAccount(accountID: Int): Flow<List<Transaction>>
}