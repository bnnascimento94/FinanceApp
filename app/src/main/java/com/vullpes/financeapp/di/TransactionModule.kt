package com.vullpes.financeapp.di

import com.vullpes.transaction.TransactionRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSourceImpl
import com.vullpes.transaction.TransactionRepository
import com.vullpes.account.domain.usecases.account.FindAccountByIdUsecase
import com.vullpes.financeapp.charts.domain.usecase.SetDayBalanceAccountUsecase
import com.vullpes.transaction.usecases.ButtonSaveTransactionEnabledUseCase
import com.vullpes.transaction.usecases.CreateTransactionUseCase
import com.vullpes.transaction.usecases.GetLastTransactionsByAccountUseCase
import com.vullpes.transaction.usecases.ListAllTransactionsByAccountUsecase
import com.vullpes.transaction.usecases.ListAllTransactionsByNameUsecase
import com.vullpes.transaction.usecases.ListTransactionByAccountDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionModule {

    @Provides
    @Singleton
    fun providesTransactionRoomDataSource(financeAppDatabase: FinanceAppDatabase): TransactionRoomDataSource {
        return TransactionRoomDataSourceImpl(financeAppDatabase)
    }

    @Provides
    @Singleton
    fun providesTransactionRepository(transactionRoomDataSource: TransactionRoomDataSource): com.vullpes.transaction.TransactionRepository {
        return com.vullpes.transaction.TransactionRepositoryImpl(transactionRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesCreateTransactionUsecase(
        transactionRepository: com.vullpes.transaction.TransactionRepository,
        findAccountByIdUsecase: com.vullpes.account.domain.usecases.account.FindAccountByIdUsecase,
        setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase
    ): com.vullpes.transaction.usecases.CreateTransactionUseCase {
        return com.vullpes.transaction.usecases.CreateTransactionUseCase(
            transactionRepository,
            findAccountByIdUsecase,
            setDayBalanceAccountUsecase
        )
    }

    @Provides
    @Singleton
    fun providesListTransactionUsecase(transactionRepository: com.vullpes.transaction.TransactionRepository): com.vullpes.transaction.usecases.ListTransactionByAccountDateUseCase {
        return com.vullpes.transaction.usecases.ListTransactionByAccountDateUseCase(
            transactionRepository
        )
    }

    @Provides
    @Singleton
    fun providesButtonSaveTransactionEnabledUseCase(): com.vullpes.transaction.usecases.ButtonSaveTransactionEnabledUseCase {
        return com.vullpes.transaction.usecases.ButtonSaveTransactionEnabledUseCase()
    }

    @Provides
    @Singleton
    fun providesGetLastTransactionsByAccountUseCase(transactionRepository: com.vullpes.transaction.TransactionRepository): com.vullpes.transaction.usecases.GetLastTransactionsByAccountUseCase {
        return com.vullpes.transaction.usecases.GetLastTransactionsByAccountUseCase(
            transactionRepository
        )
    }

    @Provides
    @Singleton
    fun providesListAllTransactionsByAccountUsecase(transactionRepository: com.vullpes.transaction.TransactionRepository): com.vullpes.transaction.usecases.ListAllTransactionsByAccountUsecase {
        return com.vullpes.transaction.usecases.ListAllTransactionsByAccountUsecase(
            transactionRepository
        )
    }

    @Provides
    @Singleton
    fun providesListAllTransactionsByNameUsecase(transactionRepository: com.vullpes.transaction.TransactionRepository): com.vullpes.transaction.usecases.ListAllTransactionsByNameUsecase {
        return com.vullpes.transaction.usecases.ListAllTransactionsByNameUsecase(
            transactionRepository
        )
    }




}