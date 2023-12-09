package com.vullpes.financeapp.domain.usecases.category

import com.vullpes.financeapp.domain.model.Category

class ButtonSaveCategoryEnabledUsecase {

    fun execute(category: Category): Boolean{
        return category.nameCategory != null
    }
}
