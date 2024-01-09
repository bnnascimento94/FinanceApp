package com.vullpes.financeapp.authentication.domain.usecases

import com.vullpes.financeapp.authentication.domain.UserRepository
import javax.inject.Inject

class LoginUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(username:String, password:String):Boolean{
       return userRepository.loginUser(email = username, password = password)
    }
}