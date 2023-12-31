package com.vullpes.financeapp.data.dataSource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vullpes.financeapp.data.dataSource.room.entities.AccountDao
import com.vullpes.financeapp.data.dataSource.room.entities.AccountDb
import com.vullpes.financeapp.data.dataSource.room.entities.CategoryDao
import com.vullpes.financeapp.data.dataSource.room.entities.CategoryDb
import com.vullpes.financeapp.data.dataSource.room.entities.DayBalanceDao
import com.vullpes.financeapp.data.dataSource.room.entities.DayBalanceDb
import com.vullpes.financeapp.data.dataSource.room.entities.TransactionDao
import com.vullpes.financeapp.data.dataSource.room.entities.TransactionDb
import com.vullpes.financeapp.data.dataSource.room.entities.UserDao
import com.vullpes.financeapp.data.dataSource.room.entities.UserDb

@Database(
    entities = [AccountDb::class, DayBalanceDb::class, CategoryDb::class, TransactionDb::class, UserDb::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class FinanceAppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun dayBalanceDao(): DayBalanceDao
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "finance_app_database"
    }
}