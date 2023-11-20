package com.vullpes.financeapp.domain.usecases.charts

import com.vullpes.financeapp.domain.ChartsRepository
import com.vullpes.financeapp.util.Months
import java.util.Date
import javax.inject.Inject

class AllCategoryBalanceByMonthUseCase @Inject constructor(private val chartsRepository: ChartsRepository) {
    operator fun invoke(accountID: Int,months: Months) = chartsRepository.getAccountBalanceByMonth(accountId = accountID, month = months)
}