package com.vullpes.financeapp.domain.usecases.account

import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.DayBalance
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.usecases.dayBalance.SetDayBalanceAccountUsecase
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