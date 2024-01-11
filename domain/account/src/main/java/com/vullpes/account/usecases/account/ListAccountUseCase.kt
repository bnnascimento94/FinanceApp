package com.vullpes.account.usecases.account

import com.vullpes.account.AccountRepository
import javax.inject.Inject

class ListAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {
    suspend fun operator(userID: Int) = accountRepository.getAccounts(userID)
}