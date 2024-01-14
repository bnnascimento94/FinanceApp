package com.vullpes.financeapp.di

import com.vullpes.account.DayBalanceRepository
import com.vullpes.account.usecases.dayBalance.SetDayBalanceAccountUsecase
import com.vullpes.account.DayBalanceRepositoryImpl
import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.repository.dayBalance.DayBalanceRoomDataSource
import com.vullpes.room.repository.dayBalance.DayBalanceRoomDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DayBalanceModule {

    @Provides
    @Singleton
    fun providesDayBalanceRoomDataSource(financeAppDatabase: FinanceAppDatabase): DayBalanceRoomDataSource {
        return DayBalanceRoomDataSourceImpl(financeAppDatabase)
    }

    @Provides
    @Singleton
    fun providesDayBalanceRepository(dayBalanceRoomDataSource: DayBalanceRoomDataSource): DayBalanceRepository {
        return DayBalanceRepositoryImpl(dayBalanceRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesSetDayBalanceAccountUsecase(dayBalanceRepository: DayBalanceRepository): SetDayBalanceAccountUsecase {
        return SetDayBalanceAccountUsecase(dayBalanceRepository)
    }



}