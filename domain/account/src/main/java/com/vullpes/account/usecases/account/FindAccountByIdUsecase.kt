package com.vullpes.account.usecases.account

import com.vullpes.account.AccountRepository
import javax.inject.Inject

class FindAccountByIdUsecase @Inject constructor(private val accountRepository: AccountRepository)  {
    suspend fun operator(accountID: Int) = accountRepository.findAccountById(accountID)
}