package com.vullpes.room.repository.dayBalance

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.entities.toDayBalance
import com.vullpes.financeapp.charts.domain.DayBalance
import javax.inject.Inject

class DayBalanceRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase):
    DayBalanceRoomDataSource {
    override suspend fun getLastDayBalance(accountID: Int): DayBalance? {
        val dayBalanceDao = financeAppDatabase.dayBalanceDao()
        return dayBalanceDao.getLastDayBalance(accountID)?.toDayBalance()
    }
}