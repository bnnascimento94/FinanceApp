package com.vullpes.financeapp.domain.usecases.authentication

import com.vullpes.financeapp.domain.UserRepository
import javax.inject.Inject

class ForgotPasswordUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(email:String) = userRepository.forgotPassword(email)
}