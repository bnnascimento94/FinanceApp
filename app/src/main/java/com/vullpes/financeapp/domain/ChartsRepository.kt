package com.vullpes.financeapp.domain

import com.vullpes.financeapp.util.Months
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ChartsRepository {
    fun getAccountBalanceByDate(accountId: Int, date1: Date, date2:Date): Flow<Resource<Map<String,Double>>>
    fun getAccountBalanceByMonth(accountId: Int, month: Months): Flow<Resource<Map<String,Double>>>
    fun getAllCategoryBalanceByDate(date1: Date, date2:Date): Flow<Resource<Map<String,Map<String?,Double>>>>
    fun getAllCategoryBalanceByAccountAndDate(accountID: Int,date1: Date, date2:Date): Flow<Resource<Map<String,Double>>>
    fun getAllCategoryBalanceByMonth(month: Months): Flow<Resource<Map<String,Map<String?,Double>>>>


}