package com.vullpes.category.usecases

import com.vullpes.category.Category

class ButtonSaveCategoryEnabledUsecase {

    fun execute(category: Category): Boolean{
        return category.nameCategory != null
    }
}
