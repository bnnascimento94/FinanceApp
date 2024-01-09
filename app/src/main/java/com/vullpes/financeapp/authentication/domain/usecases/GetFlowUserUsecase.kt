package com.vullpes.financeapp.authentication.domain.usecases

import com.vullpes.financeapp.authentication.domain.UserRepository
import com.vullpes.financeapp.authentication.domain.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFlowUserUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(): Flow<User> {
        return userRepository.currentUser()
    }
}