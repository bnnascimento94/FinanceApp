package com.vullpes.financeapp.authentication.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun createUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun updatePhoto(imageSrc: String)
    suspend fun deactivateUser(userID: Int)
    suspend fun loginUser(email:String, password:String): Boolean
    suspend fun logout()
    suspend fun getLoggedUser(): User?
    suspend fun currentUser(): Flow<User>
    suspend fun forgotPassword(email:String):Boolean

}