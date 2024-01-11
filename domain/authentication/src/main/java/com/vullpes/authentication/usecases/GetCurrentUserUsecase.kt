package com.vullpes.authentication.usecases

import com.vullpes.authentication.UserRepository
import com.vullpes.authentication.User
import javax.inject.Inject

class GetCurrentUserUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(): User? {
        return userRepository.getLoggedUser()
    }
}