package com.vullpes.room.repository.charts

import com.vullpes.transaction.Transaction
import com.vullpes.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ChartsRoomDataSource {

    fun getAccountBalanceByDate(accountId: Int, date1: Date, date2: Date): Flow<Map<String, Double>>
    fun getAllCategoryBalanceByDate(date1: Date, date2: Date): Flow<Resource<Map<String, Map<String?, Double>>>>
    fun getTransactionsByAccountAndDate(accountId: Int, date1: Date, date2: Date): Flow<Resource<Map<String, List<Transaction>>>>
}