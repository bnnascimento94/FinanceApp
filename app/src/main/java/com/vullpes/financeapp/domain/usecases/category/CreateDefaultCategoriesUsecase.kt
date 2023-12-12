package com.vullpes.financeapp.domain.usecases.category

import com.vullpes.financeapp.domain.CategoryRepository
import com.vullpes.financeapp.domain.model.Category
import javax.inject.Inject

class CreateDefaultCategoriesUsecase @Inject constructor(private val categoryRepository: CategoryRepository) {
    suspend fun execute(){
        val listCategory = arrayListOf(
            Category(
                nameCategory = "Housing",
                active = true
            ),
            Category(
                nameCategory = "Transportation",
                active = true
            ),
            Category(
                nameCategory = "Food",
                active = true
            ),
            Category(
                nameCategory = "Utilities",
                active = true
            ),
            Category(
                nameCategory = "Insurance",
                active = true
            ),
            Category(
                nameCategory = "Medical and HealthCare",
                active = true
            ),
            Category(
                nameCategory = "Saving, Investing, Debt Payments",
                active = true
            ),
            Category(
                nameCategory = "Personal Spending",
                active = true
            ),
            Category(
                nameCategory = "Recreation and Entertainment",
                active = true
            ),
            Category(
                nameCategory = "Miscellaneous",
                active = true
            ),
        )

        val categoriesSaved = categoryRepository.categoriesSaved()

        listCategory.filter {
            categoriesSaved.none { categorySaved -> categorySaved.nameCategory == it.nameCategory }
        }.forEach { category ->
            categoryRepository.createCategory(category)
        }

    }

}