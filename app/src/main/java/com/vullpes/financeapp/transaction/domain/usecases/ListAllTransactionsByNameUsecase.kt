package com.vullpes.financeapp.transaction.domain.usecases

import com.vullpes.financeapp.transaction.domain.TransactionRepository
import com.vullpes.financeapp.transaction.domain.Transaction
import com.vullpes.util.domain.dates.toLocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class ListAllTransactionsByNameUsecase @Inject constructor(private val transactionRepository: TransactionRepository){
    fun execute(transactionName: String, accountID: Int): Flow<Map<LocalDate, List<Transaction>>> {
        return transactionRepository.listAllTransactionsByAccountName(transactionName, accountID).map { transactions ->
            transactions.groupBy { transaction -> transaction.dateTransaction.toLocalDate() }
        }
    }
}