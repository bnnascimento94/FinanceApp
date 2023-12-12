package com.vullpes.financeapp.di

import com.vullpes.financeapp.data.TransactionRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.transaction.TransactionRoomDataSourceImpl
import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.usecases.account.FindAccountByIdUsecase
import com.vullpes.financeapp.domain.usecases.dayBalance.SetDayBalanceAccountUsecase
import com.vullpes.financeapp.domain.usecases.transaction.ButtonSaveTransactionEnabledUseCase
import com.vullpes.financeapp.domain.usecases.transaction.CreateTransactionUseCase
import com.vullpes.financeapp.domain.usecases.transaction.GetLastTransactionsByAccountUseCase
import com.vullpes.financeapp.domain.usecases.transaction.ListAllTransactionsByAccountUsecase
import com.vullpes.financeapp.domain.usecases.transaction.ListAllTransactionsByNameUsecase
import com.vullpes.financeapp.domain.usecases.transaction.ListTransactionByAccountDateUseCase
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
    fun providesCreateTransactionUsecase(
        transactionRepository: TransactionRepository,
        findAccountByIdUsecase: FindAccountByIdUsecase,
        setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase
    ): CreateTransactionUseCase {
        return CreateTransactionUseCase(
            transactionRepository,
            findAccountByIdUsecase,
            setDayBalanceAccountUsecase
        )
    }

    @Provides
    @Singleton
    fun providesListTransactionUsecase(transactionRepository: TransactionRepository): ListTransactionByAccountDateUseCase {
        return ListTransactionByAccountDateUseCase(transactionRepository)
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

    @Provides
    @Singleton
    fun providesListAllTransactionsByAccountUsecase(transactionRepository: TransactionRepository): ListAllTransactionsByAccountUsecase {
        return ListAllTransactionsByAccountUsecase(transactionRepository)
    }

    @Provides
    @Singleton
    fun providesListAllTransactionsByNameUsecase(transactionRepository: TransactionRepository): ListAllTransactionsByNameUsecase {
        return ListAllTransactionsByNameUsecase(transactionRepository)
    }

}