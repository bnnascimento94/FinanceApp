package com.vullpes.charts

interface DayBalanceRepository {

    suspend fun getLastDayBalance(accountID: Int): DayBalance?

}