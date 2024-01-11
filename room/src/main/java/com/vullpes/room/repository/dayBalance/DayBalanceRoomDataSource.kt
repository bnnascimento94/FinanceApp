package com.vullpes.room.repository.dayBalance

import com.vullpes.charts.DayBalance


interface DayBalanceRoomDataSource {
    suspend fun getLastDayBalance(accountID: Int): DayBalance?
}