package com.vullpes.room.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDb: UserDb)

    @Update
    fun update(userDb: UserDb)

    @Delete
    fun delete(userDb: UserDb)

    @Query("select * from userDb")
    fun getUsers(): Flow<List<UserDb>>

    @Query("select * from userDb where userID = :userID")
    suspend fun getUsersById(userID:Int): UserDb?

    @Query("select * from userDb order by userID desc limit 1")
    suspend fun getLastInsertedUser(): UserDb?

    @Query("select * from userDb where userID = :userID")
    fun getCurrentUser(userID:Int): Flow<UserDb>

    @Query("select * from userDb where email = :name and password = :password")
    suspend fun getUserByEmailAndPassword(name:String, password: String): UserDb?

    @Query("select * from userDb where email = :name")
    suspend fun getUserByEmail(name:String): UserDb?


}