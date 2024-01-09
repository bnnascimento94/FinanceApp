package com.vullpes.financeapp.charts.domain

import com.vullpes.financeapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ChartsRepository {
    fun getAccountBalanceByDate(accountId: Int, date1: Date, date2:Date): Flow<Map<String,Double>>
    fun getAllCategoryBalanceByDate(date1: Date, date2:Date): Flow<Resource<Map<String, Map<String?, Double>>>>

}