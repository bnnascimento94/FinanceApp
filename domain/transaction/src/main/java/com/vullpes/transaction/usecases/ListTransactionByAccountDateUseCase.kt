package com.vullpes.transaction.usecases

import com.vullpes.transaction.TransactionRepository
import com.vullpes.transaction.Transaction
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