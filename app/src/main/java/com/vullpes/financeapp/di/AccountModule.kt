package com.vullpes.financeapp.di

import com.vullpes.account.data.AccountRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.account.AccountRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.account.AccountRoomDataSourceImpl
import com.vullpes.account.domain.AccountRepository
import com.vullpes.transaction.TransactionRepository
import com.vullpes.account.domain.usecases.account.ActivateAccountUseCase
import com.vullpes.account.domain.usecases.account.ButtonSaveAccountEnabledUsecase
import com.vullpes.account.domain.usecases.account.CheckIfAccountNameIsDifferentUsecase
import com.vullpes.account.domain.usecases.account.CheckIfCanWithdrawUsecase
import com.vullpes.account.domain.usecases.account.CreateAccountUseCase
import com.vullpes.account.domain.usecases.account.DeactivateAccountUseCase
import com.vullpes.account.domain.usecases.account.FindAccountByIdUsecase
import com.vullpes.account.domain.usecases.account.ListAccountUseCase
import com.vullpes.account.domain.usecases.account.UpdateAccountUseCase
import com.vullpes.financeapp.charts.domain.usecase.SetDayBalanceAccountUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

    @Provides
    @Singleton
    fun providesAccountRoomDataSource(firebaseAppDatabase: FinanceAppDatabase): AccountRoomDataSource{
        return AccountRoomDataSourceImpl(firebaseAppDatabase)
    }

    @Provides
    @Singleton
    fun providesAccountRepository(accountRoomDataSource: AccountRoomDataSource): com.vullpes.account.domain.AccountRepository {
        return com.vullpes.account.data.AccountRepositoryImpl(accountRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesActivateAccountUsecase(accountRepository: com.vullpes.account.domain.AccountRepository): com.vullpes.account.domain.usecases.account.ActivateAccountUseCase {
        return com.vullpes.account.domain.usecases.account.ActivateAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesCreateAccountUsecase(accountRepository: com.vullpes.account.domain.AccountRepository,
                                     transactionRepository: com.vullpes.transaction.TransactionRepository,
                                     setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase
    ): com.vullpes.account.domain.usecases.account.CreateAccountUseCase {
        return com.vullpes.account.domain.usecases.account.CreateAccountUseCase(
            accountRepository,
            transactionRepository,
            setDayBalanceAccountUsecase
        )
    }

    @Provides
    @Singleton
    fun providesDeactivateAccountUsecase(accountRepository: com.vullpes.account.domain.AccountRepository): com.vullpes.account.domain.usecases.account.DeactivateAccountUseCase {
        return com.vullpes.account.domain.usecases.account.DeactivateAccountUseCase(
            accountRepository
        )
    }

    @Provides
    @Singleton
    fun providesListAccountUsecase(accountRepository: com.vullpes.account.domain.AccountRepository): com.vullpes.account.domain.usecases.account.ListAccountUseCase {
        return com.vullpes.account.domain.usecases.account.ListAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesUpdateAccountUsecase(accountRepository: com.vullpes.account.domain.AccountRepository, transactionRepository: com.vullpes.transaction.TransactionRepository, setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase): com.vullpes.account.domain.usecases.account.UpdateAccountUseCase {
        return com.vullpes.account.domain.usecases.account.UpdateAccountUseCase(
            accountRepository,
            transactionRepository,
            setDayBalanceAccountUsecase
        )
    }

    @Provides
    @Singleton
    fun providesButtonSaveAccountEnabledUsecase(): com.vullpes.account.domain.usecases.account.ButtonSaveAccountEnabledUsecase {
        return com.vullpes.account.domain.usecases.account.ButtonSaveAccountEnabledUsecase()
    }

    @Provides
    @Singleton
    fun providesCheckIfAccountNameIsDifferentUsecase(): com.vullpes.account.domain.usecases.account.CheckIfAccountNameIsDifferentUsecase {
        return com.vullpes.account.domain.usecases.account.CheckIfAccountNameIsDifferentUsecase()
    }

    @Provides
    @Singleton
    fun providesFindAccountByIdUsecase(accountRepository: com.vullpes.account.domain.AccountRepository): com.vullpes.account.domain.usecases.account.FindAccountByIdUsecase {
        return com.vullpes.account.domain.usecases.account.FindAccountByIdUsecase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesCheckIfCanWithdrawUsecase(): com.vullpes.account.domain.usecases.account.CheckIfCanWithdrawUsecase {
        return com.vullpes.account.domain.usecases.account.CheckIfCanWithdrawUsecase()
    }


}