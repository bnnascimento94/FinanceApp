package com.vullpes.room.repository.dayBalance

import com.vullpes.financeapp.charts.domain.DayBalance

interface DayBalanceRoomDataSource {
    suspend fun getLastDayBalance(accountID: Int): DayBalance?
}