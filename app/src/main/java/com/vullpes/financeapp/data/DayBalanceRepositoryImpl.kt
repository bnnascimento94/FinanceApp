package com.vullpes.financeapp.data

import com.vullpes.financeapp.data.dataSource.room.repository.dayBalance.DayBalanceRoomDataSource
import com.vullpes.financeapp.domain.DayBalanceRepository
import com.vullpes.financeapp.domain.model.DayBalance
import javax.inject.Inject

class DayBalanceRepositoryImpl @Inject constructor(private val dayBalanceRoomDataSource: DayBalanceRoomDataSource) : DayBalanceRepository{
    override suspend fun getLastDayBalance(accountID: Int): DayBalance? {
        return dayBalanceRoomDataSource.getLastDayBalance(accountID = accountID)
    }
}