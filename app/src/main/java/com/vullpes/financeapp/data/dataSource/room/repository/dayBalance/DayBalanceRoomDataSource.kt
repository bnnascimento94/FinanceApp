package com.vullpes.financeapp.data.dataSource.room.repository.dayBalance

import com.vullpes.financeapp.domain.model.DayBalance

interface DayBalanceRoomDataSource {
    suspend fun getLastDayBalance(accountID: Int): DayBalance?
}