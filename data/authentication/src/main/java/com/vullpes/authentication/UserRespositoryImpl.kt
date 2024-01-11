package com.vullpes.authentication

import com.vullpes.firebase.auth.AuthFirebaseDataSource
import com.vullpes.room.repository.user.UserRoomDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRespositoryImpl @Inject constructor(
    private val userRoomDataSource: UserRoomDataSource,
    private val preferenciasRepository: com.vullpes.sharedpreferences.PreferenciasRepository,
    private val authFirebaseDataSource: AuthFirebaseDataSource
): UserRepository {
    override suspend fun createUser(user: User) {
        val userRegistered = authFirebaseDataSource.registerUser(user.email, user.password)
        if(userRegistered){
            val user: User? = userRoomDataSource.createUser(user)
            user?.let {
                preferenciasRepository.saveUser(it.id)
            }
        }
    }

    override suspend fun updateUser(user: User) {
        var userSaved = userRoomDataSource.getUserById(user.id)

        if(user.email != userSaved?.email){
            authFirebaseDataSource.updateEmail(user.email)
        }

        if(user.password != userSaved?.password){
            authFirebaseDataSource.updatePassword(user.password)
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
                }?: kotlin.run {
                    val user = com.vullpes.authentication.User(
                        name = email.subSequence(
                            0,
                            email.indexOf("@")
                        ).toString(), email = email, password = password
                    )
                    val userSaved: com.vullpes.authentication.User? = userRoomDataSource.createUser(user)
                    userSaved?.let {
                        preferenciasRepository.saveUser(it.id)
                    }
                    true
                }
            }
        }else{
            false
        }

    }

    override suspend fun logout() {
        authFirebaseDataSource.logoutUser()
        preferenciasRepository.cleanData()
    }

    override suspend fun getLoggedUser(): com.vullpes.authentication.User? {
        val usedID = preferenciasRepository.getSavedUser()
        return usedID?.let {
            userRoomDataSource.getUserById(it)
        }
    }

    override suspend fun currentUser(): Flow<com.vullpes.authentication.User> {
        val usedID = preferenciasRepository.getSavedUser()
        return userRoomDataSource.getLoggedUser(usedID!!)
    }

    override suspend fun forgotPassword(email: String): Boolean {
       return authFirebaseDataSource.forgotPassword(email)
    }
}