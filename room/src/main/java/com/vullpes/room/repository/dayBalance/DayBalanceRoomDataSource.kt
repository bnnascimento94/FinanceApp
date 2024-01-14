package com.vullpes.room.repository.dayBalance

import com.vullpes.account.DayBalance


interface DayBalanceRoomDataSource {
    suspend fun getLastDayBalance(accountID: Int): DayBalance?
}