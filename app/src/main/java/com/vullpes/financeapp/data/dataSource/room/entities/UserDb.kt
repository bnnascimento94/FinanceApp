package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vullpes.financeapp.domain.model.User
import java.util.Date

@Entity
data class UserDb(
    @PrimaryKey(autoGenerate = true)
    val userID:Int,
    val name:String,
    val email: String,
    val password:String,
    val imgSrc:String? = null,
    var active:Boolean = true
)

fun UserDb.toUser() = User(
    id = userID,
    name = name,
    email = email,
    password = password,
    imgSrc = imgSrc,
    active = active
)

fun User.toUserDb() = UserDb(
    userID = id,
    name = name,
    email = email,
    password = password,
    imgSrc = imgSrc,
    active = active
)