package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface DayBalanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dayBalanceDb: DayBalanceDb)

    @Update
    fun update(dayBalanceDb: DayBalanceDb)

    @Delete
    fun delete(dayBalanceDb: DayBalanceDb)


    @Query("select * from daybalancedb where accountID = :accountID order by dayBalanceId desc limit 1")
    fun getLastDayBalance(accountID:Int): DayBalanceDb?

    @Query("select * from daybalancedb where accountID = :accountID and date = :currentDate limit 1")
    fun getDayBalance(accountID:Int,currentDate: Date): DayBalanceDb?

    @Query("select * from daybalancedb where accountID = :accountID and date between :date1 and :date2")
    fun getDayBalanceByDates(accountID:Int, date1: Date, date2: Date): Flow<List<DayBalanceDb>>
}