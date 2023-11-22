package com.vullpes.financeapp.di

import com.vullpes.financeapp.data.AccountRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.account.AccountRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.account.AccountRoomDataSourceImpl
import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.usecases.account.ActivateAccountUseCase
import com.vullpes.financeapp.domain.usecases.account.CreateAccountUseCase
import com.vullpes.financeapp.domain.usecases.account.DeactivateAccountUseCase
import com.vullpes.financeapp.domain.usecases.account.ListAccountUseCase
import com.vullpes.financeapp.domain.usecases.account.UpdateAccountUseCase
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
    fun providesAccountRepository(accountRoomDataSource: AccountRoomDataSource): AccountRepository{
        return AccountRepositoryImpl(accountRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesActivateAccountUsecase(accountRepository: AccountRepository): ActivateAccountUseCase{
        return ActivateAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesCreateAccountUsecase(accountRepository: AccountRepository): CreateAccountUseCase{
        return CreateAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesDeactivateAccountUsecase(accountRepository: AccountRepository): DeactivateAccountUseCase{
        return DeactivateAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesListAccountUsecase(accountRepository: AccountRepository): ListAccountUseCase {
        return ListAccountUseCase(accountRepository)
    }

    @Provides
    @Singleton
    fun providesUpdateAccountUsecase(accountRepository: AccountRepository): UpdateAccountUseCase {
        return UpdateAccountUseCase(accountRepository)
    }

}