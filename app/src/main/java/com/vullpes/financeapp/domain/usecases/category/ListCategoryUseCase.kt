package com.vullpes.financeapp.domain.usecases.category

import com.vullpes.financeapp.domain.CategoryRepository
import javax.inject.Inject

class ListCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    operator fun invoke() = categoryRepository.listCategories()
}