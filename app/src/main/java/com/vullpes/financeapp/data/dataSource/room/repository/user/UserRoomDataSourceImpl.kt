package com.vullpes.financeapp.data.dataSource.room.repository.user

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.entities.toUser
import com.vullpes.financeapp.data.dataSource.room.entities.toUserDb
import com.vullpes.financeapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRoomDataSourceImpl @Inject constructor(
    private val financeAppDatabase: FinanceAppDatabase
): UserRoomDataSource {
    override suspend fun createUser(user: User) {
        try {
            val userDao = financeAppDatabase.userDao()
            userDao.insert(user.toUserDb())
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun updateUser(user: User) {
        try {
            val userDao = financeAppDatabase.userDao()
            userDao.update(user.toUserDb())
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun deactivateUser(userID: Int) {
        try {
            val userDao = financeAppDatabase.userDao()
            val userDb = userDao.getUsersById(userID = userID)
            userDb?.let {
                userDao.update(it.apply { this.active = true })
            }

        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getUserById(userID: Int): User? {
       return try {
            val userDao = financeAppDatabase.userDao()
            userDao.getUsersById(userID)?.toUser()
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun loginUser(user: String, password: String): User? {
      return try{
            val userDao = financeAppDatabase.userDao()
            val userDb = userDao.getUserByUserNameAndPassword(name = user,password = password)
            userDb?.toUser()
      }catch (e:Exception){
            throw e
      }
    }

    override suspend fun getLoggedUser(userID: Int): Flow<User> {
        val userDao = financeAppDatabase.userDao()
        return userDao.getCurrentUser(userID = userID).map {
            it.toUser()
        }
    }

}