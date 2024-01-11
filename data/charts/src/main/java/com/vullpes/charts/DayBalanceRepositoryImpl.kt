package com.vullpes.charts


import com.vullpes.room.repository.dayBalance.DayBalanceRoomDataSource
import javax.inject.Inject

class DayBalanceRepositoryImpl @Inject constructor(private val dayBalanceRoomDataSource: DayBalanceRoomDataSource) :
    DayBalanceRepository {
    override suspend fun getLastDayBalance(accountID: Int): DayBalance? {
        return dayBalanceRoomDataSource.getLastDayBalance(accountID = accountID)
    }
}