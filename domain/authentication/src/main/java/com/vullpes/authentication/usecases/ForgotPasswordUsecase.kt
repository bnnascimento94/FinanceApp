package com.vullpes.authentication.usecases

import com.vullpes.authentication.UserRepository
import javax.inject.Inject

class ForgotPasswordUsecase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun execute(email:String) = userRepository.forgotPassword(email)
}