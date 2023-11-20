package com.vullpes.financeapp.domain.usecases.category

import com.vullpes.financeapp.domain.CategoryRepository
import com.vullpes.financeapp.domain.model.Category
import javax.inject.Inject

class ActivateCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(category: Category) =categoryRepository.updateCategory(category = category)
}