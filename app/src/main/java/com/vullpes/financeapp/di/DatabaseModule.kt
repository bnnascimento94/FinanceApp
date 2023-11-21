package com.vullpes.financeapp.di

import android.content.Context
import androidx.room.Room
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMonitoriaDataBase(@ApplicationContext appContext: Context): FinanceAppDatabase {
        return Room.databaseBuilder(
            appContext,
            FinanceAppDatabase::class.java,
            FinanceAppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }


}