package com.vullpes.financeapp.di

import com.vullpes.financeapp.charts.data.ChartRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.charts.ChartsRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.charts.ChartsRoomDataSourceImpl
import com.vullpes.financeapp.charts.domain.ChartsRepository
import com.vullpes.financeapp.transaction.domain.TransactionRepository
import com.vullpes.financeapp.charts.domain.usecase.AccountBalanceByDateUseCase
import com.vullpes.financeapp.charts.domain.usecase.AllCategoryBalanceByDateUseCase
import com.vullpes.financeapp.charts.domain.usecase.AllCategoryTransactionByAccountAndDateUseCase
import com.vullpes.financeapp.charts.domain.usecase.GroupTransactionsByTransferenceWithdrawalDepositUsecase
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
    fun providesChartRepository(chartsRoomDataSource: ChartsRoomDataSource): ChartsRepository {
        return ChartRepositoryImpl(chartsRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesAccountBalanceByDateUseCase(chartsRepository: ChartsRepository): AccountBalanceByDateUseCase {
        return AccountBalanceByDateUseCase(chartsRepository)
    }

    @Provides
    @Singleton
    fun providesAllCategoryBalanceByDateUseCase(chartsRepository: ChartsRepository): AllCategoryBalanceByDateUseCase {
        return AllCategoryBalanceByDateUseCase(chartsRepository)
    }

    @Provides
    @Singleton
    fun providesGroupTransactionsByTransferenceWithdrawalDepositUseCase(transactionRepository: TransactionRepository): GroupTransactionsByTransferenceWithdrawalDepositUsecase {
        return GroupTransactionsByTransferenceWithdrawalDepositUsecase(transactionRepository)
    }


    @Provides
    @Singleton
    fun providesAllCategoryTransactionByAccountAndDateUseCase(transactionRepository: TransactionRepository): AllCategoryTransactionByAccountAndDateUseCase {
        return AllCategoryTransactionByAccountAndDateUseCase(transactionRepository)
    }




}