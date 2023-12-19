package com.vullpes.financeapp.domain.usecases.authentication

import com.vullpes.financeapp.domain.UserRepository
import javax.inject.Inject

class LoginUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(username:String, password:String):Boolean{
       return userRepository.loginUser(email = username, password = password)
    }
}