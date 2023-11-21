package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class CategoryDb(
    @PrimaryKey
    val categoryID: Int,
    val nameCategory:String,
    val active:Boolean,
    val dataCreation: Date
)
