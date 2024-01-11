package com.vullpes.charts.usecase

import com.vullpes.transaction.Transaction
import com.vullpes.transaction.TransactionRepository
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