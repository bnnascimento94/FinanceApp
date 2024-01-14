package com.vullpes.room.repository.dayBalance

import com.vullpes.account.DayBalance
import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.entities.toDayBalance
import javax.inject.Inject

class DayBalanceRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase):
    DayBalanceRoomDataSource {
    override suspend fun getLastDayBalance(accountID: Int): DayBalance? {
        val dayBalanceDao = financeAppDatabase.dayBalanceDao()
        return dayBalanceDao.getLastDayBalance(accountID)?.toDayBalance()
    }
}