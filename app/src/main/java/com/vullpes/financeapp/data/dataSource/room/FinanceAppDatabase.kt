package com.vullpes.financeapp.data.dataSource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vullpes.financeapp.data.dataSource.room.entities.AccountDao
import com.vullpes.financeapp.data.dataSource.room.entities.AccountDb
import com.vullpes.financeapp.data.dataSource.room.entities.CategoryDao
import com.vullpes.financeapp.data.dataSource.room.entities.CategoryDb
import com.vullpes.financeapp.data.dataSource.room.entities.TransactionDao
import com.vullpes.financeapp.data.dataSource.room.entities.TransactionDb

@Database(
    entities =[AccountDb::class, CategoryDb::class, TransactionDb::class], version = 1, exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class FinanceAppDatabase:RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}