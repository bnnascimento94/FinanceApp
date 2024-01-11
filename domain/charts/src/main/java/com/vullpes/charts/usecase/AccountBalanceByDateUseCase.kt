package com.vullpes.charts.usecase

import com.vullpes.charts.ChartsRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class AccountBalanceByDateUseCase @Inject constructor(private val chartsRepository: ChartsRepository) {

    operator fun invoke(accountId: Int, date1: Date, date2:Date) : Flow<Map<String,Double>> {

        return chartsRepository.getAccountBalanceByDate(accountId = accountId,date1= date1,date2=date2)

    }




}