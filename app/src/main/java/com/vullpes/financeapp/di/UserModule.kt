package com.vullpes.financeapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.itm.juipdv.util.imagem.ImageSaver
import com.vullpes.authentication.UserRespositoryImpl
import com.vullpes.firebase.auth.AuthFirebaseDataSource
import com.vullpes.firebase.auth.AuthFirebaseDataSourceImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSourceImpl
import com.vullpes.sharedpreferences.PreferenciasRepository
import com.vullpes.authentication.UserRepository
import com.vullpes.authentication.usecases.AllowBiometricsUsecase
import com.vullpes.util.domain.biometrics.BiometricAuthenticationUsecase
import com.vullpes.util.domain.biometrics.CheckBiometricSupportUsecase
import com.vullpes.authentication.usecases.ClearAllSessionDataUsecase
import com.vullpes.authentication.usecases.CreateUserUseCase
import com.vullpes.authentication.usecases.GetBiometricStatusUsecase
import com.vullpes.authentication.usecases.GetCurrentUserUsecase
import com.vullpes.authentication.usecases.GetFlowUserUsecase
import com.vullpes.authentication.usecases.LoginUsecase
import com.vullpes.authentication.usecases.LogoutUsecase
import com.vullpes.authentication.usecases.UpdatePhotoImageUsecase
import com.vullpes.authentication.usecases.UpdateUserUsercase
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
        imageSaver: ImageSaver
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
    fun providesCheckBiometricSupportUsecase(@ApplicationContext context: Context): com.vullpes.util.domain.biometrics.CheckBiometricSupportUsecase {
        return com.vullpes.util.domain.biometrics.CheckBiometricSupportUsecase(context)
    }

    @Provides
    @Singleton
    fun providesBiometricAuthenticationUsecase(@ApplicationContext context: Context): com.vullpes.util.domain.biometrics.BiometricAuthenticationUsecase {
        return com.vullpes.util.domain.biometrics.BiometricAuthenticationUsecase(context)
    }
}