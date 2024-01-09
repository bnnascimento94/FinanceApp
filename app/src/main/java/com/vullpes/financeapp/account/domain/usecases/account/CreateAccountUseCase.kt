package com.vullpes.financeapp.account.domain.usecases.account

import com.vullpes.financeapp.account.domain.AccountRepository
import com.vullpes.financeapp.transaction.domain.TransactionRepository
import com.vullpes.financeapp.account.domain.Account
import com.vullpes.financeapp.charts.domain.DayBalance
import com.vullpes.financeapp.transaction.domain.Transaction
import com.vullpes.financeapp.charts.domain.usecase.SetDayBalanceAccountUsecase
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase
) {
    suspend operator fun invoke(account: Account) {
        val account = accountRepository.createAccount(account = account)

        val transaction = Transaction(
            name = "First Deposit",
            accountFromID = account.accountID,
            accountFromName = account.accountName,
            deposit = true,
            withdrawal = false,
            categoryID = 0,
            categoryName = "",
            value = account.accountBalance
        )
        val dayBalance: DayBalance = setDayBalanceAccountUsecase.execute(account)
        transactionRepository.createTransaction(
            transaction = transaction!!,
            account = account,
            accountTo = null,
            transactionTransference = null,
            dayBalance = dayBalance,
            null
        )

    }
}