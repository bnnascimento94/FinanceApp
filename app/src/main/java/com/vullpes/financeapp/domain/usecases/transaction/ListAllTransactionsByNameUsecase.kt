package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import javax.inject.Inject

class ListAllTransactionsByNameUsecase @Inject constructor(private val transactionRepository: TransactionRepository){
    fun execute(transactionName: String) = transactionRepository.listAllTransactionsByAccountName(transactionName)
}