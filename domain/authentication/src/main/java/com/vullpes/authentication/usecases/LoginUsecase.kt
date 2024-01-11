package com.vullpes.authentication.usecases

import com.vullpes.authentication.UserRepository
import javax.inject.Inject

class LoginUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(username:String, password:String):Boolean{
       return userRepository.loginUser(email = username, password = password)
    }
}