package com.vullpes.authentication.usecases

import com.vullpes.authentication.UserRepository
import com.vullpes.authentication.User
import javax.inject.Inject

class UpdateUserUsercase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(userID: Int,username:String, email:String,password:String){
        userRepository.updateUser(User(id = userID,name= username, email = email, password = password))
    }



}