package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import javax.inject.Inject

class GetLastTransactionsByAccountUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    fun execute(accountID: Int) = transactionRepository.getLastTransactionsByAccount(accountID = accountID)
}