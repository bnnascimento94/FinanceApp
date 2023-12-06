package com.vullpes.financeapp.domain.usecases.authentication

import android.graphics.Bitmap
import com.itm.juipdv.util.imagem.ImageSaver
import com.vullpes.financeapp.domain.UserRepository
import com.vullpes.financeapp.domain.model.User
import java.util.UUID
import javax.inject.Inject

class UpdateUserUsercase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun execute(username:String, email:String,password:String){

        userRepository.updateUser(User(name= username, email = email, password = password))

    }



}