package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import java.util.Date
import javax.inject.Inject

class ListTransactionUseCase @Inject constructor(private val transactionRepository: TransactionRepository){

    suspend operator fun invoke(accountID: Int, date: Date) = transactionRepository.listTransactions(accountID,date)
}