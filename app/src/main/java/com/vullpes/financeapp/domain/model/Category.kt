package com.vullpes.financeapp.domain.model

import java.util.Date

data class Category(
    val categoryID: Int,
    val nameCategory:String,
    val active:Boolean,
    val dataCreation: Date,
)