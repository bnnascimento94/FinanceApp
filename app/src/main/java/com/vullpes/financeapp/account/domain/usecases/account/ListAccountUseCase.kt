package com.vullpes.financeapp.account.domain.usecases.account

import com.vullpes.financeapp.account.domain.AccountRepository
import javax.inject.Inject

class ListAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend fun operator(userID: Int) = accountRepository.getAccounts(userID)
}