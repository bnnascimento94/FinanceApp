package com.vullpes.financeapp.account.domain.usecases.account

import com.vullpes.financeapp.account.domain.AccountRepository
import javax.inject.Inject

class FindAccountByIdUsecase @Inject constructor(private val accountRepository: AccountRepository)  {
    suspend fun operator(accountID: Int) = accountRepository.findAccountById(accountID)
}