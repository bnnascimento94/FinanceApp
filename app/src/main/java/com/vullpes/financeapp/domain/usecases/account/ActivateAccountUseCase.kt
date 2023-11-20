package com.vullpes.financeapp.domain.usecases.account

import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.model.Account
import javax.inject.Inject

class ActivateAccountUseCase @Inject constructor(private val accountRepository: AccountRepository){

    suspend operator fun invoke(account: Account) = accountRepository.updateAccount(account)

}