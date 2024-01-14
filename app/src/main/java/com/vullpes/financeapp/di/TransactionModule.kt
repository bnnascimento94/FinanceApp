package com.vullpes.financeapp.di

import com.vullpes.account.usecases.account.FindAccountByIdUsecase
import com.vullpes.account.usecases.dayBalance.SetDayBalanceAccountUsecase
import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.repository.transaction.TransactionRoomDataSource
import com.vullpes.room.repository.transaction.TransactionRoomDataSourceImpl
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
        findAccountByIdUsecase: FindAccountByIdUsecase,
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