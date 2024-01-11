package com.vullpes.account.usecases.account

import com.vullpes.account.AccountRepository
import com.vullpes.account.Account
import javax.inject.Inject

class ActivateAccountUseCase @Inject constructor(private val accountRepository: AccountRepository){

    suspend operator fun invoke(account: Account) = accountRepository.updateAccount(account)

}