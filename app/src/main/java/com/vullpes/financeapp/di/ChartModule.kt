package com.vullpes.financeapp.di

import com.vullpes.charts.ChartsRepository
import com.vullpes.charts.usecase.AccountBalanceByDateUseCase
import com.vullpes.charts.usecase.AllCategoryBalanceByDateUseCase
import com.vullpes.charts.usecase.AllCategoryTransactionByAccountAndDateUseCase
import com.vullpes.charts.usecase.GroupTransactionsByTransferenceWithdrawalDepositUsecase
import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.repository.charts.ChartsRoomDataSource
import com.vullpes.room.repository.charts.ChartsRoomDataSourceImpl
import com.vullpes.transaction.TransactionRepository
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
    fun providesChartsRoomDataSource(financeAppDatabase: FinanceAppDatabase): ChartsRoomDataSource {
        return ChartsRoomDataSourceImpl(financeAppDatabase)
    }

    @Provides
    @Singleton
    fun providesChartRepository(chartsRoomDataSource: ChartsRoomDataSource): ChartsRepository {
        return com.vullpes.charts.ChartRepositoryImpl(chartsRoomDataSource)
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