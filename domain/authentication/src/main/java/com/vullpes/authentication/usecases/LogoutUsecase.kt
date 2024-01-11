package com.vullpes.authentication.usecases

import com.vullpes.authentication.UserRepository
import javax.inject.Inject

class LogoutUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(){
        userRepository.logout()
    }
}