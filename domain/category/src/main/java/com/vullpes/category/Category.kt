package com.vullpes.category

import java.util.Date

data class Category(
    val categoryID: Int = 0,
    val nameCategory:String? = null,
    val active:Boolean = false,
    val dataCreation: Date = Date(),
)