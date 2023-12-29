package com.vullpes.financeapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.itm.juipdv.util.imagem.ImageSaver
import com.vullpes.financeapp.data.UserRespositoryImpl
import com.vullpes.financeapp.data.dataSource.firebase.auth.AuthFirebaseDataSource
import com.vullpes.financeapp.data.dataSource.firebase.auth.AuthFirebaseDataSourceImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSourceImpl
import com.vullpes.financeapp.data.sharedPreferences.PreferenciasRepository
import com.vullpes.financeapp.domain.UserRepository
import com.vullpes.financeapp.domain.usecases.authentication.AllowBiometricsUsecase
import com.vullpes.financeapp.domain.usecases.authentication.BiometricAuthenticationUsecase
import com.vullpes.financeapp.domain.usecases.authentication.CheckBiometricSupportUsecase
import com.vullpes.financeapp.domain.usecases.authentication.ClearAllSessionDataUsecase
import com.vullpes.financeapp.domain.usecases.authentication.CreateUserUseCase
import com.vullpes.financeapp.domain.usecases.authentication.GetBiometricStatusUsecase
import com.vullpes.financeapp.domain.usecases.authentication.GetCurrentUserUsecase
import com.vullpes.financeapp.domain.usecases.authentication.GetFlowUserUsecase
import com.vullpes.financeapp.domain.usecases.authentication.LoginUsecase
import com.vullpes.financeapp.domain.usecases.authentication.LogoutUsecase
import com.vullpes.financeapp.domain.usecases.authentication.UpdatePhotoImageUsecase
import com.vullpes.financeapp.domain.usecases.authentication.UpdateUserUsercase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun providesUserRoomDataSource(financeAppDatabase: FinanceAppDatabase): UserRoomDataSource {
        return UserRoomDataSourceImpl(financeAppDatabase)
    }

    @Provides
    @Singleton
    fun providesAuthFirebaseDataSource(auth: FirebaseAuth): AuthFirebaseDataSource {
        return AuthFirebaseDataSourceImpl(auth)
    }

    @Provides
    @Singleton
    fun providesUserRepository(
        userRoomDataSource: UserRoomDataSource,
        preferenciasRepository: PreferenciasRepository,
        authFirebaseDataSource: AuthFirebaseDataSource
    ): UserRepository {
        return UserRespositoryImpl(userRoomDataSource, preferenciasRepository, authFirebaseDataSource)
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
    fun providesUpdatePhotoImageUsecase(
        userRepository: UserRepository,
        imageSaver: ImageSaver
    ): UpdatePhotoImageUsecase {
        return UpdatePhotoImageUsecase(userRepository, imageSaver)
    }

    @Provides
    @Singleton
    fun providesUpdateUserUseCase(userRepository: UserRepository): UpdateUserUsercase {
        return UpdateUserUsercase(userRepository)
    }

    @Provides
    @Singleton
    fun providesLogoutUserUseCase(userRepository: UserRepository): LogoutUsecase {
        return LogoutUsecase(userRepository)
    }


    @Provides
    @Singleton
    fun providesGetFlowUserUseCase(userRepository: UserRepository): GetFlowUserUsecase {
        return GetFlowUserUsecase(userRepository)
    }

    @Provides
    @Singleton
    fun providesAllowBiometricsUsecase(preferenciasRepository: PreferenciasRepository): AllowBiometricsUsecase {
        return AllowBiometricsUsecase(preferenciasRepository)
    }

    @Provides
    @Singleton
    fun providesGetBiometricStatusUsecase(preferenciasRepository: PreferenciasRepository): GetBiometricStatusUsecase {
        return GetBiometricStatusUsecase(preferenciasRepository)
    }

    @Provides
    @Singleton
    fun providesClearAllSessionDataUsecase(preferenciasRepository: PreferenciasRepository): ClearAllSessionDataUsecase {
        return ClearAllSessionDataUsecase(preferenciasRepository)
    }


    @Provides
    @Singleton
    fun providesCheckBiometricSupportUsecase(@ApplicationContext context: Context): CheckBiometricSupportUsecase {
        return CheckBiometricSupportUsecase(context)
    }

    @Provides
    @Singleton
    fun providesBiometricAuthenticationUsecase(@ApplicationContext context: Context): BiometricAuthenticationUsecase {
        return BiometricAuthenticationUsecase(context)
    }
}