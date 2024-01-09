package com.vullpes.financeapp.di

import com.vullpes.financeapp.account.data.AccountRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.account.AccountRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.account.AccountRoomDataSourceImpl
import com.vullpes.financeapp.account.domain.AccountRepository
import com.vullpes.financeapp.transaction.domain.TransactionRepository
import com.vullpes.financeapp.account.domain.usecases.account.ActivateAccountUseCase
import com.vullpes.financeapp.account.domain.usecases.account.ButtonSaveAccountEnabledUsecase
import com.vullpes.financeapp.account.domain.usecases.account.CheckIfAccountNameIsDifferentUsecase
import com.vullpes.financeapp.account.domain.usecases.account.CheckIfCanWithdrawUsecase
import com.vullpes.financeapp.account.domain.usecases.account.CreateAccountUseCase
import com.vullpes.financeapp.account.domain.usecases.account.DeactivateAccountUseCase
import com.vullpes.financeapp.account.domain.usecases.account.FindAccountByIdUsecase
import com.vullpes.financeapp.account.domain.usecases.account.ListAccountUseCase
import com.vullpes.financeapp.account.domain.usecases.account.UpdateAccountUseCase
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
    fun providesAccountRepository(accountRoomDataSource: AccountRoomDataSource): AccountRepository {
        return AccountRepositoryImpl(accountRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesActivateAccountUsecase(accountRepository: AccountRepository): ActivateAccountUseCase {
        return ActivateAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesCreateAccountUsecase(accountRepository: AccountRepository,
                                     transactionRepository: TransactionRepository,
                                     setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase
    ): CreateAccountUseCase {
        return CreateAccountUseCase(accountRepository,transactionRepository, setDayBalanceAccountUsecase)
    }

    @Provides
    @Singleton
    fun providesDeactivateAccountUsecase(accountRepository: AccountRepository): DeactivateAccountUseCase {
        return DeactivateAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesListAccountUsecase(accountRepository: AccountRepository): ListAccountUseCase {
        return ListAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesUpdateAccountUsecase(accountRepository: AccountRepository, transactionRepository: TransactionRepository, setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase): UpdateAccountUseCase {
        return UpdateAccountUseCase(accountRepository,transactionRepository, setDayBalanceAccountUsecase)
    }

    @Provides
    @Singleton
    fun providesButtonSaveAccountEnabledUsecase(): ButtonSaveAccountEnabledUsecase {
        return ButtonSaveAccountEnabledUsecase()
    }

    @Provides
    @Singleton
    fun providesCheckIfAccountNameIsDifferentUsecase(): CheckIfAccountNameIsDifferentUsecase {
        return CheckIfAccountNameIsDifferentUsecase()
    }

    @Provides
    @Singleton
    fun providesFindAccountByIdUsecase(accountRepository: AccountRepository): FindAccountByIdUsecase {
        return FindAccountByIdUsecase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesCheckIfCanWithdrawUsecase(): CheckIfCanWithdrawUsecase {
        return CheckIfCanWithdrawUsecase()
    }


}