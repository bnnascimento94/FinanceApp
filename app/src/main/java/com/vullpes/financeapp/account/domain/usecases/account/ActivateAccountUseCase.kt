package com.vullpes.financeapp.account.domain.usecases.account

import com.vullpes.financeapp.account.domain.AccountRepository
import com.vullpes.financeapp.account.domain.Account
import javax.inject.Inject

class ActivateAccountUseCase @Inject constructor(private val accountRepository: AccountRepository){

    suspend operator fun invoke(account: Account) = accountRepository.updateAccount(account)

}