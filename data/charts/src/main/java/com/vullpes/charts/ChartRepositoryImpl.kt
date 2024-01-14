package com.vullpes.charts

import com.vullpes.room.repository.charts.ChartsRoomDataSource
import com.vullpes.common.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(private val chartsRoomDataSource: ChartsRoomDataSource):
    ChartsRepository {
    override fun getAccountBalanceByDate(
        accountId: Int,
        date1: Date,
        date2: Date
    ): Flow<Map<String, Double>> {
        return chartsRoomDataSource.getAccountBalanceByDate(accountId = accountId, date1 = date1,date2= date2)
    }

    override fun getAllCategoryBalanceByDate(
        date1: Date,
        date2: Date
    ): Flow<com.vullpes.common.Resource<Map<String, Map<String?, Double>>>> {
        return chartsRoomDataSource.getAllCategoryBalanceByDate(date1, date2)
    }

}