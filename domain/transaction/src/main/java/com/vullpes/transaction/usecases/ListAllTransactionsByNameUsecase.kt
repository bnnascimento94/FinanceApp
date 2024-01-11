package com.vullpes.transaction.usecases

import com.vullpes.transaction.TransactionRepository
import com.vullpes.transaction.Transaction
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