package com.vullpes.financeapp.authentication.domain.usecases

import com.vullpes.financeapp.authentication.domain.UserRepository
import com.vullpes.financeapp.authentication.domain.User
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(username:String, email:String,password:String){
        val user = User(name = username, email = email, password = password, active = true)
        userRepository.createUser(user)
    }
}