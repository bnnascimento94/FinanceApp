package com.vullpes.financeapp.di

import com.vullpes.financeapp.category.data.CategoryRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.category.CategoryRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.category.CategoryRoomDataSourceImpl
import com.vullpes.financeapp.category.domain.CategoryRepository
import com.vullpes.financeapp.category.domain.usecases.ButtonSaveCategoryEnabledUsecase
import com.vullpes.financeapp.category.domain.usecases.UpdateCategoryUseCase
import com.vullpes.financeapp.category.domain.usecases.CreateCategoryUseCase
import com.vullpes.financeapp.category.domain.usecases.CreateDefaultCategoriesUsecase
import com.vullpes.financeapp.category.domain.usecases.DeactivateCategoryUseCase
import com.vullpes.financeapp.category.domain.usecases.ListCategoryUseCase
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

    @Provides
    @Singleton
    fun providesButtonSaveCategoryEnabledUsecase(): ButtonSaveCategoryEnabledUsecase {
        return ButtonSaveCategoryEnabledUsecase()
    }

    @Provides
    @Singleton
    fun providesCreateDefaultCategoriesUsecase(categoryRepository: CategoryRepository): CreateDefaultCategoriesUsecase {
        return CreateDefaultCategoriesUsecase(categoryRepository)
    }

}