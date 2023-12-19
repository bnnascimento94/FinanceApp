package com.vullpes.financeapp.domain.usecases.account

import com.vullpes.financeapp.domain.AccountRepository
import javax.inject.Inject

class ListAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend fun operator(userID: Int) = accountRepository.getAccounts(userID)
}