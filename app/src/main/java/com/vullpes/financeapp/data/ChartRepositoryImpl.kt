package com.vullpes.financeapp.data

import com.vullpes.financeapp.data.dataSource.room.repository.charts.ChartsRoomDataSource
import com.vullpes.financeapp.domain.ChartsRepository
import com.vullpes.financeapp.util.Months
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(private val chartsRoomDataSource: ChartsRoomDataSource): ChartsRepository {
    override fun getAccountBalanceByDate(
        accountId: Int,
        date1: Date,
        date2: Date
    ): Flow<Resource<Map<String, Double>>> {
        TODO("Not yet implemented")
    }

    override fun getAccountBalanceByMonth(
        accountId: Int,
        month: Months
    ): Flow<Resource<Map<String, Double>>> {
        TODO("Not yet implemented")
    }

    override fun getAllCategoryBalanceByDate(
        date1: Date,
        date2: Date
    ): Flow<Resource<Map<String, Map<String, Double>>>> {
        TODO("Not yet implemented")
    }

    override fun getAllCategoryBalanceByMonth(month: Months): Flow<Resource<Map<String, Map<String, Double>>>> {
        TODO("Not yet implemented")
    }
}