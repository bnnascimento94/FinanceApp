package com.vullpes.financeapp.charts.domain

interface DayBalanceRepository {

    suspend fun getLastDayBalance(accountID: Int): DayBalance?

}