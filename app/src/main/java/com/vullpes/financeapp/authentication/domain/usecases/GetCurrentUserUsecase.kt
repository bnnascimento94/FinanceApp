package com.vullpes.financeapp.authentication.domain.usecases

import com.vullpes.financeapp.authentication.domain.UserRepository
import com.vullpes.financeapp.authentication.domain.User
import javax.inject.Inject

class GetCurrentUserUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(): User? {
        return userRepository.getLoggedUser()
    }
}