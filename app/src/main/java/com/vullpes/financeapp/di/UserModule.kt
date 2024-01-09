package com.vullpes.financeapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.itm.juipdv.util.imagem.ImageSaver
import com.vullpes.financeapp.authentication.data.UserRespositoryImpl
import com.vullpes.firebase.auth.AuthFirebaseDataSource
import com.vullpes.firebase.auth.AuthFirebaseDataSourceImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSourceImpl
import com.vullpes.sharedpreferences.PreferenciasRepository
import com.vullpes.financeapp.authentication.domain.UserRepository
import com.vullpes.financeapp.authentication.domain.usecases.AllowBiometricsUsecase
import com.vullpes.util.domain.biometrics.BiometricAuthenticationUsecase
import com.vullpes.util.domain.biometrics.CheckBiometricSupportUsecase
import com.vullpes.financeapp.authentication.domain.usecases.ClearAllSessionDataUsecase
import com.vullpes.financeapp.authentication.domain.usecases.CreateUserUseCase
import com.vullpes.financeapp.authentication.domain.usecases.GetBiometricStatusUsecase
import com.vullpes.financeapp.authentication.domain.usecases.GetCurrentUserUsecase
import com.vullpes.financeapp.authentication.domain.usecases.GetFlowUserUsecase
import com.vullpes.financeapp.authentication.domain.usecases.LoginUsecase
import com.vullpes.financeapp.authentication.domain.usecases.LogoutUsecase
import com.vullpes.financeapp.authentication.domain.usecases.UpdatePhotoImageUsecase
import com.vullpes.financeapp.authentication.domain.usecases.UpdateUserUsercase
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
    fun providesAllowBiometricsUsecase(preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository): AllowBiometricsUsecase {
        return AllowBiometricsUsecase(preferenciasRepository)
    }

    @Provides
    @Singleton
    fun providesGetBiometricStatusUsecase(preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository): GetBiometricStatusUsecase {
        return GetBiometricStatusUsecase(preferenciasRepository)
    }

    @Provides
    @Singleton
    fun providesClearAllSessionDataUsecase(preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository): ClearAllSessionDataUsecase {
        return ClearAllSessionDataUsecase(preferenciasRepository)
    }


    @Provides
    @Singleton
    fun providesCheckBiometricSupportUsecase(@ApplicationContext context: Context): com.vullpes.util.domain.biometrics.CheckBiometricSupportUsecase {
        return com.vullpes.util.domain.biometrics.CheckBiometricSupportUsecase(context)
    }

    @Provides
    @Singleton
    fun providesBiometricAuthenticationUsecase(@ApplicationContext context: Context): com.vullpes.util.domain.biometrics.BiometricAuthenticationUsecase {
        return com.vullpes.util.domain.biometrics.BiometricAuthenticationUsecase(context)
    }
}