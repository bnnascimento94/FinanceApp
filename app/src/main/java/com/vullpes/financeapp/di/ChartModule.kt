package com.vullpes.financeapp.di

import com.vullpes.financeapp.data.ChartRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.charts.ChartsRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.charts.ChartsRoomDataSourceImpl
import com.vullpes.financeapp.domain.ChartsRepository
import com.vullpes.financeapp.domain.usecases.charts.AccountBalanceByDateUseCase
import com.vullpes.financeapp.domain.usecases.charts.AccountBalanceByMonthUseCase
import com.vullpes.financeapp.domain.usecases.charts.AllCategoryBalanceByDateUseCase
import com.vullpes.financeapp.domain.usecases.charts.AllCategoryBalanceByMonthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChartModule {

    @Provides
    @Singleton
    fun providesChartsRoomDataSource(financeAppDatabase: FinanceAppDatabase):ChartsRoomDataSource{
        return ChartsRoomDataSourceImpl(financeAppDatabase)
    }

    @Provides
    @Singleton
    fun providesChartRepository(chartsRoomDataSource: ChartsRoomDataSource): ChartsRepository{
        return ChartRepositoryImpl(chartsRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesAccountBalanceByDateUseCase(chartsRepository: ChartsRepository):AccountBalanceByDateUseCase{
        return AccountBalanceByDateUseCase(chartsRepository)
    }

    @Provides
    @Singleton
    fun providesAccountBalanceByMonthUseCase(chartsRepository: ChartsRepository): AccountBalanceByMonthUseCase {
        return AccountBalanceByMonthUseCase(chartsRepository)
    }


    @Provides
    @Singleton
    fun providesAllCategoryBalanceByDateUseCase(chartsRepository: ChartsRepository): AllCategoryBalanceByDateUseCase {
        return AllCategoryBalanceByDateUseCase(chartsRepository)
    }

    @Provides
    @Singleton
    fun providesAllCategoryBalanceByMonthUseCase(chartsRepository: ChartsRepository): AllCategoryBalanceByMonthUseCase {
        return AllCategoryBalanceByMonthUseCase(chartsRepository)
    }
}