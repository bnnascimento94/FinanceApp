package com.vullpes.financeapp.authentication.domain.usecases

import com.vullpes.financeapp.authentication.domain.UserRepository
import javax.inject.Inject

class LogoutUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(){
        userRepository.logout()
    }
}