package com.vullpes.financeapp.di

import com.vullpes.financeapp.data.DayBalanceRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.dayBalance.DayBalanceRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.dayBalance.DayBalanceRoomDataSourceImpl
import com.vullpes.financeapp.domain.DayBalanceRepository
import com.vullpes.financeapp.domain.usecases.dayBalance.SetDayBalanceAccountUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DayBalanceModule {

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