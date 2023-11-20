package com.vullpes.financeapp.domain.usecases.account

import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.model.Account
import javax.inject.Inject

class DeactivateAccountUseCase @Inject constructor(private val accountRepository: AccountRepository)  {
    suspend fun operator(account: Account) = accountRepository.updateAccount(account = account)
}