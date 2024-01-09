package com.vullpes.financeapp.category.domain.usecases

import com.vullpes.financeapp.category.domain.Category

class ButtonSaveCategoryEnabledUsecase {

    fun execute(category: Category): Boolean{
        return category.nameCategory != null
    }
}
