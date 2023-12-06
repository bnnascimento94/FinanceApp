package com.vullpes.financeapp.di

import com.itm.juipdv.util.imagem.ImageSaver
import com.vullpes.financeapp.data.UserRespositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSourceImpl
import com.vullpes.financeapp.data.sharedPreferences.PreferenciasRepository
import com.vullpes.financeapp.domain.UserRepository
import com.vullpes.financeapp.domain.usecases.authentication.CreateUserUseCase
import com.vullpes.financeapp.domain.usecases.authentication.GetCurrentUserUsecase
import com.vullpes.financeapp.domain.usecases.authentication.LoginUsecase
import com.vullpes.financeapp.domain.usecases.authentication.UpdatePhotoImageUsecase
import com.vullpes.financeapp.domain.usecases.authentication.UpdateUserUsercase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun providesUserRoomDataSource(firebaseAppDatabase: FinanceAppDatabase): UserRoomDataSource {
        return UserRoomDataSourceImpl(firebaseAppDatabase)
    }

    @Provides
    @Singleton
    fun providesUserRepository(userRoomDataSource: UserRoomDataSource, preferenciasRepository: PreferenciasRepository): UserRepository {
        return UserRespositoryImpl(userRoomDataSource, preferenciasRepository)
    }

    @Provides
    @Singleton
    fun providesCreateUsecase(userRepository: UserRepository): CreateUserUseCase {
        return CreateUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun providesGetCurrentUserUsecase(userRepository: UserRepository): GetCurrentUserUsecase {
        return GetCurrentUserUsecase(userRepository)
    }

    @Provides
    @Singleton
    fun providesLoginUsecase(userRepository: UserRepository): LoginUsecase {
        return LoginUsecase(userRepository)
    }

    @Provides
    @Singleton
    fun providesUpdatePhotoImageUsecase(userRepository: UserRepository, imageSaver: ImageSaver): UpdatePhotoImageUsecase {
        return UpdatePhotoImageUsecase(userRepository, imageSaver)
    }

    @Provides
    @Singleton
    fun providesUpdateUserUseCase(userRepository: UserRepository): UpdateUserUsercase {
        return UpdateUserUsercase(userRepository)
    }

}