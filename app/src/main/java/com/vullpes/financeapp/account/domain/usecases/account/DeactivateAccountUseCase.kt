package com.vullpes.financeapp.account.domain.usecases.account

import com.vullpes.financeapp.account.domain.AccountRepository
import com.vullpes.financeapp.account.domain.Account
import javax.inject.Inject

class DeactivateAccountUseCase @Inject constructor(private val accountRepository: AccountRepository)  {
    suspend fun operator(account: Account) = accountRepository.updateAccount(account = account)
}