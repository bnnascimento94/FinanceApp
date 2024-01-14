package com.vullpes.financeapp.di


import com.vullpes.account.AccountRepository
import com.vullpes.account.AccountRepositoryImpl
import com.vullpes.account.usecases.account.ActivateAccountUseCase
import com.vullpes.account.usecases.account.ButtonSaveAccountEnabledUsecase
import com.vullpes.account.usecases.account.CheckIfAccountNameIsDifferentUsecase
import com.vullpes.transaction.usecases.CheckIfCanWithdrawUsecase
import com.vullpes.transaction.usecases.CreateAccountUseCase
import com.vullpes.account.usecases.account.DeactivateAccountUseCase
import com.vullpes.account.usecases.account.FindAccountByIdUsecase
import com.vullpes.account.usecases.account.ListAccountUseCase
import com.vullpes.transaction.usecases.UpdateAccountUseCase
import com.vullpes.account.usecases.dayBalance.SetDayBalanceAccountUsecase
import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.repository.account.AccountRoomDataSource
import com.vullpes.room.repository.account.AccountRoomDataSourceImpl
import com.vullpes.transaction.TransactionRepository
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
    fun providesAccountRoomDataSource(firebaseAppDatabase: FinanceAppDatabase): AccountRoomDataSource {
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
                                     transactionRepository: com.vullpes.transaction.TransactionRepository,
                                     setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase
    ): CreateAccountUseCase {
        return CreateAccountUseCase(
            accountRepository,
            transactionRepository,
            setDayBalanceAccountUsecase
        )
    }

    @Provides
    @Singleton
    fun providesDeactivateAccountUsecase(accountRepository: AccountRepository): DeactivateAccountUseCase {
        return DeactivateAccountUseCase(
            accountRepository
        )
    }

    @Provides
    @Singleton
    fun providesListAccountUsecase(accountRepository: AccountRepository): ListAccountUseCase {
        return ListAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesUpdateAccountUsecase(accountRepository: AccountRepository, transactionRepository: TransactionRepository, setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase): UpdateAccountUseCase {
        return UpdateAccountUseCase(
            accountRepository,
            transactionRepository,
            setDayBalanceAccountUsecase
        )
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