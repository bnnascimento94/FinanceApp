package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.data.dataSource.room.entities.AccountDb
import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.usecases.account.FindAccountByIdUsecase
import java.util.Date
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val findAccountByIdUsecase: FindAccountByIdUsecase
) {

    suspend operator fun invoke(
        transaction: Transaction
    ) {
        try {
            var account = findAccountByIdUsecase.operator(transaction.accountFromID)
            var currentValue = account?.accountBalance

            var accountTo: Account? = transaction.accountTo?.let { findAccountByIdUsecase.operator(it) }
            var transactionTransference: Transaction? = null
            var accountToValue: Double? = accountTo?.accountBalance

            if (currentValue != null) {
                if (transaction.deposit) {
                    currentValue += transaction.value
                } else if (transaction.withdrawal) {
                    currentValue -= transaction.value
                } else if (transaction.transference) {
                    transactionTransference = Transaction(
                        name = "Transference From: ${account?.accountName}",
                        accountFromID = accountTo!!.accountID,
                        accountFromName = accountTo.accountName ?: "",
                        deposit = true,
                        categoryID = 0,
                        categoryName = "",
                        value = transaction.value
                    )
                    currentValue -= transaction.value
                    accountToValue = accountToValue?.plus(transaction.value)
                }
            }
            account = account?.copy(accountBalance = currentValue?:0.0)
            accountTo = accountTo?.copy(accountBalance = accountToValue!!)
            transactionRepository.createTransaction(
                transaction = transaction,
                account = account!!,
                accountTo = accountTo,
                transactionTransference = transactionTransference
            )
        }catch (e:Exception){
            throw e
        }

    }
}