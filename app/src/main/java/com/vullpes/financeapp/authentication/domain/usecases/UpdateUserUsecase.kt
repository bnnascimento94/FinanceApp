package com.vullpes.financeapp.authentication.domain.usecases

import com.vullpes.financeapp.authentication.domain.UserRepository
import com.vullpes.financeapp.authentication.domain.User
import javax.inject.Inject

class UpdateUserUsercase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(userID: Int,username:String, email:String,password:String){
        userRepository.updateUser(User(id = userID,name= username, email = email, password = password))
    }



}