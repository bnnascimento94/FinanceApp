package com.vullpes.category.usecases

import com.vullpes.category.CategoryRepository
import javax.inject.Inject

class ListCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    operator fun invoke() = categoryRepository.listCategories()
}