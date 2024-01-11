package com.vullpes.charts.usecase

import com.vullpes.account.Account
import com.vullpes.charts.DayBalance
import com.vullpes.charts.DayBalanceRepository
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class SetDayBalanceAccountUsecase @Inject constructor(private val repository: DayBalanceRepository) {

    suspend fun execute(account: Account): DayBalance {
        val dayBalance =  repository.getLastDayBalance(account.accountID)

        return dayBalance?.let {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val currentDate = dateFormat.format(Date())
            val balanceDate = dateFormat.format(it.date)

            if(currentDate == balanceDate){
                val dayAccountUpdated = dayBalance.copy(finalBalance = account.accountBalance)
                dayAccountUpdated
            }else{
                DayBalance(
                    dayBalanceId =0,
                    accountID = account.accountID,
                    date = Date(),
                    finalBalance = account.accountBalance

                )
            }
        }?: DayBalance(
            dayBalanceId =0,
            accountID = account.accountID,
            date = Date(),
            finalBalance = account.accountBalance)
    }
}