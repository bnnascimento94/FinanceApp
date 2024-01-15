package com.vullpes.financeapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.repository.user.UserRoomDataSource
import com.vullpes.room.repository.user.UserRoomDataSourceImpl
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
    fun providesAuthFirebaseDataSource(auth: FirebaseAuth): com.vullpes.firebase.auth.AuthFirebaseDataSource {
        return com.vullpes.firebase.auth.AuthFirebaseDataSourceImpl(auth)
    }

    @Provides
    @Singleton
    fun providesUserRepository(
        userRoomDataSource: UserRoomDataSource,
        preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository,
        authFirebaseDataSource: com.vullpes.firebase.auth.AuthFirebaseDataSource
    ): com.vullpes.authentication.UserRepository {
        return com.vullpes.authentication.UserRespositoryImpl(
            userRoomDataSource,
            preferenciasRepository,
            authFirebaseDataSource
        )
    }

    @Provides
    @Singleton
    fun providesCreateUsecase(userRepository: com.vullpes.authentication.UserRepository): com.vullpes.authentication.usecases.CreateUserUseCase {
        return com.vullpes.authentication.usecases.CreateUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun providesGetCurrentUserUsecase(userRepository: com.vullpes.authentication.UserRepository): com.vullpes.authentication.usecases.GetCurrentUserUsecase {
        return com.vullpes.authentication.usecases.GetCurrentUserUsecase(userRepository)
    }

    @Provides
    @Singleton
    fun providesLoginUsecase(userRepository: com.vullpes.authentication.UserRepository): com.vullpes.authentication.usecases.LoginUsecase {
        return com.vullpes.authentication.usecases.LoginUsecase(userRepository)
    }

    @Provides
    @Singleton
    fun providesUpdatePhotoImageUsecase(
        userRepository: com.vullpes.authentication.UserRepository,
        imageSaver: com.vullpes.common.domain.imagem.ImageSaver
    ): com.vullpes.authentication.usecases.UpdatePhotoImageUsecase {
        return com.vullpes.authentication.usecases.UpdatePhotoImageUsecase(
            userRepository,
            imageSaver
        )
    }

    @Provides
    @Singleton
    fun providesUpdateUserUseCase(userRepository: com.vullpes.authentication.UserRepository): com.vullpes.authentication.usecases.UpdateUserUsercase {
        return com.vullpes.authentication.usecases.UpdateUserUsercase(userRepository)
    }

    @Provides
    @Singleton
    fun providesLogoutUserUseCase(userRepository: com.vullpes.authentication.UserRepository): com.vullpes.authentication.usecases.LogoutUsecase {
        return com.vullpes.authentication.usecases.LogoutUsecase(userRepository)
    }


    @Provides
    @Singleton
    fun providesGetFlowUserUseCase(userRepository: com.vullpes.authentication.UserRepository): com.vullpes.authentication.usecases.GetFlowUserUsecase {
        return com.vullpes.authentication.usecases.GetFlowUserUsecase(userRepository)
    }

    @Provides
    @Singleton
    fun providesAllowBiometricsUsecase(preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository): com.vullpes.authentication.usecases.AllowBiometricsUsecase {
        return com.vullpes.authentication.usecases.AllowBiometricsUsecase(preferenciasRepository)
    }

    @Provides
    @Singleton
    fun providesGetBiometricStatusUsecase(preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository): com.vullpes.authentication.usecases.GetBiometricStatusUsecase {
        return com.vullpes.authentication.usecases.GetBiometricStatusUsecase(preferenciasRepository)
    }

    @Provides
    @Singleton
    fun providesClearAllSessionDataUsecase(preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository): com.vullpes.authentication.usecases.ClearAllSessionDataUsecase {
        return com.vullpes.authentication.usecases.ClearAllSessionDataUsecase(preferenciasRepository)
    }


    @Provides
    @Singleton
    fun providesCheckBiometricSupportUsecase(@ApplicationContext context: Context): com.vullpes.common.domain.biometrics.CheckBiometricSupportUsecase {
        return com.vullpes.common.domain.biometrics.CheckBiometricSupportUsecase(context)
    }

    @Provides
    @Singleton
    fun providesBiometricAuthenticationUsecase(@ApplicationContext context: Context): com.vullpes.common.domain.biometrics.BiometricAuthenticationUsecase {
        return com.vullpes.common.domain.biometrics.BiometricAuthenticationUsecase(context)
    }
}