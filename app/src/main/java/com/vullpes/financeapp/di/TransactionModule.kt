package com.vullpes.financeapp.di

import com.vullpes.financeapp.data.TransactionRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSourceImpl
import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.usecases.transaction.ButtonSaveTransactionEnabledUseCase
import com.vullpes.financeapp.domain.usecases.transaction.CreateTransactionUseCase
import com.vullpes.financeapp.domain.usecases.transaction.DeleteTransactionUseCase
import com.vullpes.financeapp.domain.usecases.transaction.GetLastTransactionsByAccountUseCase
import com.vullpes.financeapp.domain.usecases.transaction.ListTransactionUseCase
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
    fun providesTransactionRepository(transactionRoomDataSource: TransactionRoomDataSource): TransactionRepository {
        return TransactionRepositoryImpl(transactionRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesCreateTransactionUsecase(transactionRepository: TransactionRepository): CreateTransactionUseCase {
        return CreateTransactionUseCase(transactionRepository)
    }

    @Provides
    @Singleton
    fun providesDeleteTransactionUsecase(transactionRepository: TransactionRepository): DeleteTransactionUseCase {
        return DeleteTransactionUseCase(transactionRepository)
    }

    @Provides
    @Singleton
    fun providesListTransactionUsecase(transactionRepository: TransactionRepository): ListTransactionUseCase {
        return ListTransactionUseCase(transactionRepository)
    }

    @Provides
    @Singleton
    fun providesButtonSaveTransactionEnabledUseCase(): ButtonSaveTransactionEnabledUseCase {
        return ButtonSaveTransactionEnabledUseCase()
    }

    @Provides
    @Singleton
    fun providesGetLastTransactionsByAccountUseCase(transactionRepository: TransactionRepository): GetLastTransactionsByAccountUseCase {
        return GetLastTransactionsByAccountUseCase(transactionRepository)
    }

}