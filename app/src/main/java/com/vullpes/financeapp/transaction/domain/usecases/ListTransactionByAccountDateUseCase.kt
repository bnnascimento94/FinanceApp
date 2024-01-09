package com.vullpes.financeapp.transaction.domain.usecases

import com.vullpes.financeapp.transaction.domain.TransactionRepository
import com.vullpes.financeapp.transaction.domain.Transaction
import com.vullpes.util.domain.dates.toLocalDate
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