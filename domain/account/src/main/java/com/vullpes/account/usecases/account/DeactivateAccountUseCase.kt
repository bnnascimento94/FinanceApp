package com.vullpes.account.usecases.account

import com.vullpes.account.AccountRepository
import com.vullpes.account.Account
import javax.inject.Inject

class DeactivateAccountUseCase @Inject constructor(private val accountRepository: AccountRepository)  {
    suspend fun operator(account: Account) = accountRepository.updateAccount(account = account)
}