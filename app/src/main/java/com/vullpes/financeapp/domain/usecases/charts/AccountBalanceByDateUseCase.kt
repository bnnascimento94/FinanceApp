package com.vullpes.financeapp.domain.usecases.charts

import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.ChartsRepository
import java.util.Date
import javax.inject.Inject

class AccountBalanceByDateUseCase @Inject constructor(private val chartsRepository: ChartsRepository) {

    operator fun invoke(accountId: Int, date1: Date, date2:Date) = chartsRepository.getAccountBalanceByDate(accountId = accountId,date1= date1,date2=date2)

}