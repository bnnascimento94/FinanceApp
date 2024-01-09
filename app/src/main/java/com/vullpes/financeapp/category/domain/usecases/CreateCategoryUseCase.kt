package com.vullpes.financeapp.category.domain.usecases

import com.vullpes.financeapp.category.domain.CategoryRepository
import com.vullpes.financeapp.category.domain.Category
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository)  {
    suspend operator fun invoke(category: Category) = categoryRepository.createCategory(category = category)
}