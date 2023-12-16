package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.util.toLocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

class ListTransactionByAccountDateUseCase @Inject constructor(private val transactionRepository: TransactionRepository){

    operator fun invoke(accountID: Int, date: Date) : Flow<Map<LocalDate, List<Transaction>>> {
        return transactionRepository.listTransactions(accountID,date).map { transactions ->
            transactions.groupBy { transaction -> transaction.dateTransaction.toLocalDate() }
        }
    }


}