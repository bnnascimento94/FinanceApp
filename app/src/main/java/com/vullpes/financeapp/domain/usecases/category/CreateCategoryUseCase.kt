package com.vullpes.financeapp.domain.usecases.category

import com.vullpes.financeapp.domain.CategoryRepository
import com.vullpes.financeapp.domain.model.Category
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository)  {
    suspend operator fun invoke(category: Category) = categoryRepository.createCategory(category = category)
}