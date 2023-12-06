package com.vullpes.financeapp.domain.usecases.authentication

import com.vullpes.financeapp.domain.UserRepository
import com.vullpes.financeapp.domain.model.User
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(username:String, email:String,password:String){
        val user = User(name = username, email = email, password = password, active = true)
        userRepository.createUser(user)
    }
}