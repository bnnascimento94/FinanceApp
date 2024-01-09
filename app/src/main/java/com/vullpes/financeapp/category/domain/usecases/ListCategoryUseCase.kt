package com.vullpes.financeapp.category.domain.usecases

import com.vullpes.financeapp.category.domain.CategoryRepository
import javax.inject.Inject

class ListCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    operator fun invoke() = categoryRepository.listCategories()
}