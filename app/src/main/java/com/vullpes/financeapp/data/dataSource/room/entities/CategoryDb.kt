package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vullpes.financeapp.domain.model.Category
import java.util.Date

@Entity
data class CategoryDb(
    @PrimaryKey(autoGenerate = true)
    val categoryID: Int,
    val nameCategory:String,
    val active:Boolean,
    val dataCreation: Date
)

fun Category.toCategoryDb() = CategoryDb(
    categoryID, nameCategory?:"", active, dataCreation
)

fun CategoryDb.toCategory() = Category(
    categoryID, nameCategory, active, dataCreation
)
