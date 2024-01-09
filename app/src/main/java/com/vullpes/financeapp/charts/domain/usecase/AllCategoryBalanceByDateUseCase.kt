package com.vullpes.financeapp.charts.domain.usecase

import com.vullpes.financeapp.charts.domain.ChartsRepository
import java.util.Date
import javax.inject.Inject

class AllCategoryBalanceByDateUseCase @Inject constructor(private val chartsRepository: ChartsRepository) {
    operator fun invoke(date1:Date, date2: Date) = chartsRepository.getAllCategoryBalanceByDate(date1 = date1, date2 = date2)
}