package com.vullpes.financeapp.di

import com.vullpes.charts.DayBalanceRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.dayBalance.DayBalanceRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.dayBalance.DayBalanceRoomDataSourceImpl
import com.vullpes.financeapp.charts.domain.DayBalanceRepository
import com.vullpes.financeapp.charts.domain.usecase.SetDayBalanceAccountUsecase
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
        return com.vullpes.charts.DayBalanceRepositoryImpl(dayBalanceRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesSetDayBalanceAccountUsecase(dayBalanceRepository: DayBalanceRepository): SetDayBalanceAccountUsecase {
        return SetDayBalanceAccountUsecase(dayBalanceRepository)
    }



}