package com.vullpes.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vullpes.room.entities.AccountDao
import com.vullpes.room.entities.AccountDb
import com.vullpes.room.entities.CategoryDao
import com.vullpes.room.entities.CategoryDb
import com.vullpes.room.entities.DayBalanceDao
import com.vullpes.room.entities.DayBalanceDb
import com.vullpes.room.entities.TransactionDao
import com.vullpes.room.entities.TransactionDb
import com.vullpes.room.entities.UserDao
import com.vullpes.room.entities.UserDb

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