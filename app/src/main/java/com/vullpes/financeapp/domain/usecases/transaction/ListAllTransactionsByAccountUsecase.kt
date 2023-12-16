package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.util.toLocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class ListAllTransactionsByAccountUsecase @Inject constructor(private val transactionRepository: TransactionRepository){
    fun execute(accountID: Int): Flow<Map<LocalDate, List<Transaction>>> {
        return transactionRepository.getAllTransactionsByAccount(accountID).map { transactions ->
            transactions.groupBy { transaction -> transaction.dateTransaction.toLocalDate() }
        }
    }
}