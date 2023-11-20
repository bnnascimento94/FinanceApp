package com.vullpes.financeapp.domain.usecases.charts

import com.vullpes.financeapp.domain.ChartsRepository
import com.vullpes.financeapp.util.Months
import javax.inject.Inject

class AccountBalanceByMonthUseCase @Inject constructor(private val chartsRepository: ChartsRepository){
    operator fun invoke(accountID: Int, month: Months) = chartsRepository.getAccountBalanceByMonth(accountId = accountID, month = month)
}