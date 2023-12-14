package com.vullpes.financeapp.domain.usecases.charts

import android.util.Log
import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import java.util.Date
import javax.inject.Inject

class AllCategoryTransactionByAccountAndDateUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {
    suspend fun execute(accountID: Int, date1: Date, date2: Date) : Map<String?, Double> {
        val transactions = transactionRepository.listTransactionsByAccountAndDate(accountID, date1, date2)

        return transactions.filter { it.categoryName != null}
            .groupBy { it.categoryName }
            .mapValues { calculateFinalBalance(it.value) }

    }

    private fun calculateFinalBalance(transactions: List<Transaction>?): Double {
        var finalbalance = 0.0
        transactions?.forEach { accountTransaction ->
            finalbalance += accountTransaction.value
        }
        return finalbalance
    }


}