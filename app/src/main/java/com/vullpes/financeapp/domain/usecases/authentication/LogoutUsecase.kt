package com.vullpes.financeapp.domain.usecases.authentication

import com.vullpes.financeapp.domain.UserRepository
import javax.inject.Inject

class LogoutUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(){
        userRepository.logout()
    }
}