package com.vullpes.financeapp.domain

import com.vullpes.financeapp.domain.model.DayBalance

interface DayBalanceRepository {

    suspend fun getLastDayBalance(accountID: Int): DayBalance?

}