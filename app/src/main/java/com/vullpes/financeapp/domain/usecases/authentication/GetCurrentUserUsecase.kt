package com.vullpes.financeapp.domain.usecases.authentication

import com.vullpes.financeapp.domain.UserRepository
import com.vullpes.financeapp.domain.model.User
import javax.inject.Inject

class GetCurrentUserUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(): User? {
        return userRepository.getLoggedUser()
    }
}