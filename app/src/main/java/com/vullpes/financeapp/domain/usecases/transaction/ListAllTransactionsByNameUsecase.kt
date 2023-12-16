package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.util.toLocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class ListAllTransactionsByNameUsecase @Inject constructor(private val transactionRepository: TransactionRepository){
    fun execute(transactionName: String): Flow<Map<LocalDate, List<Transaction>>> {
        return transactionRepository.listAllTransactionsByAccountName(transactionName).map { transactions ->
            transactions.groupBy { transaction -> transaction.dateTransaction.toLocalDate() }
        }
    }
}