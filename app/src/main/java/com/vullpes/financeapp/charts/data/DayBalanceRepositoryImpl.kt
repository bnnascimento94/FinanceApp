package com.vullpes.financeapp.charts.data

import com.vullpes.financeapp.data.dataSource.room.repository.dayBalance.DayBalanceRoomDataSource
import com.vullpes.financeapp.charts.domain.DayBalanceRepository
import com.vullpes.financeapp.charts.domain.DayBalance
import javax.inject.Inject

class DayBalanceRepositoryImpl @Inject constructor(private val dayBalanceRoomDataSource: DayBalanceRoomDataSource) :
    DayBalanceRepository {
    override suspend fun getLastDayBalance(accountID: Int): DayBalance? {
        return dayBalanceRoomDataSource.getLastDayBalance(accountID = accountID)
    }
}