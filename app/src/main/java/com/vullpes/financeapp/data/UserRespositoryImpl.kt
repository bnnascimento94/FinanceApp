package com.vullpes.financeapp.data

import android.util.Log
import com.vullpes.financeapp.data.dataSource.firebase.auth.AuthFirebaseDataSource
import com.vullpes.financeapp.data.dataSource.room.entities.toUserDb
import com.vullpes.financeapp.data.dataSource.room.repository.user.UserRoomDataSource
import com.vullpes.financeapp.data.sharedPreferences.PreferenciasRepository
import com.vullpes.financeapp.domain.UserRepository
import com.vullpes.financeapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRespositoryImpl @Inject constructor(
    private val userRoomDataSource: UserRoomDataSource,
    private val preferenciasRepository: PreferenciasRepository,
    private val authFirebaseDataSource: AuthFirebaseDataSource
): UserRepository {
    override suspend fun createUser(user: User) {
        val userRegistered = authFirebaseDataSource.registerUser(user.email, user.password)
        if(userRegistered){
            userRoomDataSource.createUser(user)
        }

    }

    override suspend fun updateUser(user: User) {
        var userSaved = userRoomDataSource.getUserById(user.id)

        if(user.email != userSaved?.email){
            authFirebaseDataSource.registerUser(user.email, user.password)
        }

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

    override suspend fun loginUser(email: String, password: String): Boolean {
        return if(authFirebaseDataSource.buscarLogin(email,password)){
            val user = userRoomDataSource.loginUser(email, password)
            if(user != null){
                preferenciasRepository.saveUser(userID = user.id)
                true
            }else{
                var user = userRoomDataSource.userByEmail(email = email)
                user = user?.copy(email = email, password = password)
                user?.let {
                    updateUser(it)
                    true
                }?:false
            }
        }else{
            false
        }

    }

    override suspend fun logout() {
        authFirebaseDataSource.logoutUser()
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