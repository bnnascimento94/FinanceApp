package com.vullpes.financeapp.charts.data

import com.vullpes.financeapp.data.dataSource.room.repository.charts.ChartsRoomDataSource
import com.vullpes.financeapp.charts.domain.ChartsRepository
import com.vullpes.financeapp.domain.util.Resource
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
    ): Flow<Resource<Map<String, Map<String?, Double>>>> {
        return chartsRoomDataSource.getAllCategoryBalanceByDate(date1, date2)
    }

}