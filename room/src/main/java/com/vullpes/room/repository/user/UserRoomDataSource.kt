package com.vullpes.room.repository.user


import com.vullpes.authentication.User
import kotlinx.coroutines.flow.Flow

interface UserRoomDataSource {

    suspend fun createUser(user: User): User?
    suspend fun updateUser(user: User)
    suspend fun deactivateUser(userID: Int)
    suspend fun getUserById(userID: Int): User?
    suspend fun loginUser(user:String, password:String): User?

    suspend fun userByEmail(email:String): User?
    suspend fun getLoggedUser(userID:Int): Flow<User>


}