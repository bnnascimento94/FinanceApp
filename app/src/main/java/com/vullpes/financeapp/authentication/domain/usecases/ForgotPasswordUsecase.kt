package com.vullpes.financeapp.authentication.domain.usecases

import com.vullpes.financeapp.authentication.domain.UserRepository
import javax.inject.Inject

class ForgotPasswordUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(email:String) = userRepository.forgotPassword(email)
}