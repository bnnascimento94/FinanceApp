package com.vullpes.financeapp.domain.usecases.charts

import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.ChartsRepository
import com.vullpes.financeapp.domain.util.BegginingEndMonth
import com.vullpes.financeapp.util.Months
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import javax.inject.Inject

class AccountBalanceByDateUseCase @Inject constructor(private val chartsRepository: ChartsRepository) {

    operator fun invoke(accountId: Int, date1: Date, date2:Date) : Flow<Map<String,Double>> {

        return chartsRepository.getAccountBalanceByDate(accountId = accountId,date1= date1,date2=date2)

    }




}