package com.vullpes.financeapp.di

import android.content.Context
import android.content.SharedPreferences
import com.vullpes.common.domain.imagem.ImageSaverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    @Singleton
    fun providesImageSaver(@ApplicationContext context: Context): com.vullpes.common.domain.imagem.ImageSaver {
        return ImageSaverImpl(context)
    }

    @Provides
    @Singleton
    fun providesPreferenciasRepository(@ApplicationContext context: Context): com.vullpes.sharedpreferences.PreferenciasRepository {
        val preferences: SharedPreferences = context.getSharedPreferences("dadosUsuario", 0)
        val editor: SharedPreferences.Editor = preferences.edit()
        return com.vullpes.sharedpreferences.PreferenciasRepositoryImpl(preferences, editor)
    }
}