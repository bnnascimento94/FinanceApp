package com.vullpes.authentication.usecases

import com.vullpes.authentication.UserRepository
import com.vullpes.authentication.User
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(username:String, email:String,password:String){
        val user = User(name = username, email = email, password = password, active = true)
        userRepository.createUser(user)
    }
}