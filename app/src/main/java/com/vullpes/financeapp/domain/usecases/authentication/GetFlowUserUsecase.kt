package com.vullpes.financeapp.domain.usecases.authentication

import com.vullpes.financeapp.domain.UserRepository
import com.vullpes.financeapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFlowUserUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(): Flow<User> {
        return userRepository.currentUser()
    }
}