package com.vullpes.financeapp.di

import com.vullpes.category.CategoryRepositoryImpl
import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.repository.category.CategoryRoomDataSource
import com.vullpes.financeapp.data.dataSource.room.repository.category.CategoryRoomDataSourceImpl
import com.vullpes.category.CategoryRepository
import com.vullpes.category.usecases.ButtonSaveCategoryEnabledUsecase
import com.vullpes.category.usecases.UpdateCategoryUseCase
import com.vullpes.category.usecases.CreateCategoryUseCase
import com.vullpes.category.usecases.CreateDefaultCategoriesUsecase
import com.vullpes.category.usecases.DeactivateCategoryUseCase
import com.vullpes.category.usecases.ListCategoryUseCase
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
    fun providesCategoryRepository(categoryRoomDataSource: CategoryRoomDataSource): com.vullpes.category.CategoryRepository {
        return com.vullpes.category.CategoryRepositoryImpl(categoryRoomDataSource)
    }

    @Provides
    @Singleton
    fun providesActivateCategoryUsecase(categoryRepository: com.vullpes.category.CategoryRepository): com.vullpes.category.usecases.UpdateCategoryUseCase {
        return com.vullpes.category.usecases.UpdateCategoryUseCase(categoryRepository)
    }

    @Provides
    @Singleton
    fun providesCreateCategoryUsecase(categoryRepository: com.vullpes.category.CategoryRepository): com.vullpes.category.usecases.CreateCategoryUseCase {
        return com.vullpes.category.usecases.CreateCategoryUseCase(categoryRepository)
    }

    @Provides
    @Singleton
    fun providesDeactivateCategoryUsecase(categoryRepository: com.vullpes.category.CategoryRepository): com.vullpes.category.usecases.DeactivateCategoryUseCase {
        return com.vullpes.category.usecases.DeactivateCategoryUseCase(categoryRepository)
    }

    @Provides
    @Singleton
    fun providesListCategoryUsecase(categoryRepository: com.vullpes.category.CategoryRepository): com.vullpes.category.usecases.ListCategoryUseCase {
        return com.vullpes.category.usecases.ListCategoryUseCase(categoryRepository)
    }

    @Provides
    @Singleton
    fun providesButtonSaveCategoryEnabledUsecase(): com.vullpes.category.usecases.ButtonSaveCategoryEnabledUsecase {
        return com.vullpes.category.usecases.ButtonSaveCategoryEnabledUsecase()
    }

    @Provides
    @Singleton
    fun providesCreateDefaultCategoriesUsecase(categoryRepository: com.vullpes.category.CategoryRepository): com.vullpes.category.usecases.CreateDefaultCategoriesUsecase {
        return com.vullpes.category.usecases.CreateDefaultCategoriesUsecase(categoryRepository)
    }

}