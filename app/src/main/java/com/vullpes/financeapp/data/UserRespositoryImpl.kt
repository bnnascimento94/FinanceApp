package com.vullpes.financeapp.data

import android.util.Log
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSource
import com.vullpes.financeapp.data.sharedPreferences.PreferenciasRepository
import com.vullpes.financeapp.domain.UserRepository
import com.vullpes.financeapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRespositoryImpl @Inject constructor(
    private val userRoomDataSource: UserRoomDataSource,
    private val preferenciasRepository: PreferenciasRepository
): UserRepository {
    override suspend fun createUser(user: User) {
        userRoomDataSource.createUser(user)
    }

    override suspend fun updateUser(user: User) {
        var userSaved = userRoomDataSource.getUserById(user.id)

        userSaved = userSaved?.copy(
            name= user.name,
            email = user.email,
            password = user.password,
            active = user.active
        )
        userSaved?.let {
            userRoomDataSource.updateUser(it)
        }
    }

    override suspend fun updatePhoto(imageSrc: String) {
        val userID = preferenciasRepository.getSavedUser()
        var userSaved = userID?.let { userRoomDataSource.getUserById(it) }

        userSaved = userSaved?.copy(
            imgSrc = imageSrc
        )
        userSaved?.let { userRoomDataSource.updateUser(it) }
    }

    override suspend fun deactivateUser(userID: Int) {
        userRoomDataSource.deactivateUser(userID)
    }

    override suspend fun loginUser(user: String, password: String): Boolean {
        val user = userRoomDataSource.loginUser(user, password)
        return user?.let {
            preferenciasRepository.saveUser(userID = it.id)
            true
        }?:false
    }

    override suspend fun logout() {
        preferenciasRepository.cleanData()
    }

    override suspend fun getLoggedUser(): User? {
        val usedID = preferenciasRepository.getSavedUser()
        return usedID?.let {
            userRoomDataSource.getUserById(it)
        }
    }

    override suspend fun currentUser(): Flow<User> {
        val usedID = preferenciasRepository.getSavedUser()
        return userRoomDataSource.getLoggedUser(usedID!!)
    }
}