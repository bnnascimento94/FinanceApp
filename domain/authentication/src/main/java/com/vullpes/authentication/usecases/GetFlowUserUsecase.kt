package com.vullpes.authentication.usecases

import com.vullpes.authentication.UserRepository
import com.vullpes.authentication.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFlowUserUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(): Flow<User> {
        return userRepository.currentUser()
    }
}