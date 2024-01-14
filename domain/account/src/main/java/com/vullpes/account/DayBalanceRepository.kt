package com.vullpes.account

import com.vullpes.account.DayBalance

interface DayBalanceRepository {

    suspend fun getLastDayBalance(accountID: Int): DayBalance?

}