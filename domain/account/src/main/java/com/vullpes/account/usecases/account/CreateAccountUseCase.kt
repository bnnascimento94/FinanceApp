package com.vullpes.account.usecases.account

import com.vullpes.account.Account
import com.vullpes.account.AccountRepository
import com.vullpes.charts.DayBalance
import com.vullpes.charts.usecase.SetDayBalanceAccountUsecase
import com.vullpes.transaction.Transaction
import com.vullpes.transaction.TransactionRepository
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