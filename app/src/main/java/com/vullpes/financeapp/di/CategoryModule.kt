package com.vullpes.financeapp.di

import com.vullpes.financeapp.data.CategoryRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.category.CategoryRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.category.CategoryRoomDataSourceImpl
import com.vullpes.financeapp.domain.CategoryRepository
import com.vullpes.financeapp.domain.usecases.category.UpdateCategoryUseCase
import com.vullpes.financeapp.domain.usecases.category.CreateCategoryUseCase
import com.vullpes.financeapp.domain.usecases.category.DeactivateCategoryUseCase
import com.vullpes.financeapp.domain.usecases.category.ListCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {

    @Provides
    @Singleton
    fun providesCategoryRoomDataSource(firebaseAppDatabase: FinanceAppDatabase): CategoryRoomDataSource {
        return CategoryRoomDataSourceImpl(firebaseAppDatabase)
    }

    @Provides
    @Singleton
    fun providesCategoryRepository(categoryRoomDataSource: CategoryRoomDataSource): CategoryRepository {
        return CategoryRepositoryImpl(categoryRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesActivateCategoryUsecase(categoryRepository: CategoryRepository): UpdateCategoryUseCase {
        return UpdateCategoryUseCase(categoryRepository)
    }

    @Provides
    @Singleton
    fun providesCreateCategoryUsecase(categoryRepository: CategoryRepository): CreateCategoryUseCase {
        return CreateCategoryUseCase(categoryRepository)
    }

    @Provides
    @Singleton
    fun providesDeactivateCategoryUsecase(categoryRepository: CategoryRepository): DeactivateCategoryUseCase {
        return DeactivateCategoryUseCase(categoryRepository)
    }

    @Provides
    @Singleton
    fun providesListCategoryUsecase(categoryRepository: CategoryRepository): ListCategoryUseCase {
        return ListCategoryUseCase(categoryRepository)
    }

}