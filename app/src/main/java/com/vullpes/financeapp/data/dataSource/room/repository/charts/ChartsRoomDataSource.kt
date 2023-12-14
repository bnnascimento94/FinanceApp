package com.vullpes.financeapp.data.dataSource.room.repository.charts

import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.util.Months
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ChartsRoomDataSource {

    fun getAccountBalanceByDate(accountId: Int, date1: Date, date2: Date): Flow<Map<String, Double>>
    fun getAllCategoryBalanceByAccountAndDate(accountId: Int,date1: Date, date2: Date): Flow<Resource<Map<String, Double>>>
    fun getAllCategoryBalanceByDate(date1: Date, date2: Date): Flow<Resource<Map<String, Map<String?, Double>>>>
    fun getAllCategoryBalanceByMonth(month: Months): Flow<Resource<Map<String, Map<String?, Double>>>>
    fun getTransactionsByAccountAndDate(accountId: Int, date1: Date, date2: Date): Flow<Resource<Map<String, List<Transaction>>>>
}