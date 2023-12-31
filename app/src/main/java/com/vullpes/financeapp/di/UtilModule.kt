package com.vullpes.financeapp.di

import android.content.Context
import android.content.SharedPreferences
import com.itm.juipdv.util.imagem.ImageSaver
import com.itm.juipdv.util.imagem.ImageSaverImpl
import com.vullpes.financeapp.data.sharedPreferences.PreferenciasRepository
import com.vullpes.financeapp.data.sharedPreferences.PreferenciasRepositoryImpl
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
    fun providesImageSaver(@ApplicationContext context: Context): ImageSaver {
        return ImageSaverImpl(context)
    }

    @Provides
    @Singleton
    fun providesPreferenciasRepository(@ApplicationContext context: Context): PreferenciasRepository {
        val preferences: SharedPreferences = context.getSharedPreferences("dadosUsuario", 0)
        val editor: SharedPreferences.Editor = preferences.edit()
        return PreferenciasRepositoryImpl(preferences, editor)
    }
}