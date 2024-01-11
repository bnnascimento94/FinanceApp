package com.vullpes.category.usecases

import com.vullpes.category.CategoryRepository
import com.vullpes.category.Category
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository)  {
    suspend operator fun invoke(category: Category) = categoryRepository.createCategory(category = category)
}