package com.vullpes.financeapp.transaction.domain.usecases

import com.vullpes.financeapp.transaction.domain.TransactionRepository
import javax.inject.Inject

class GetLastTransactionsByAccountUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    fun execute(accountID: Int) = transactionRepository.getLastTransactionsByAccount(accountID = accountID)
}