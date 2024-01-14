package com.vullpes.authentication.usecases

import android.graphics.Bitmap
import com.vullpes.authentication.UserRepository
import com.vullpes.common.domain.imagem.ImageSaver
import java.util.UUID
import javax.inject.Inject

class UpdatePhotoImageUsecase @Inject constructor(
    private val userRepository: UserRepository,
    private val imageSaver: com.vullpes.common.domain.imagem.ImageSaver
) {

    suspend fun execute(photo: Bitmap, oldImage:String?){
        oldImage?.let {
            deletePicture(it)
        }
        val imageSrc = savePicture(photo,"profile_img")
        if (imageSrc != null) {
            userRepository.updatePhoto(imageSrc)
        }

    }

    private fun savePicture(bitmap: Bitmap, directory:String): String?{
        return try {
            val hash = UUID.randomUUID().toString()
            val imageFileName = "IMAGE_$hash.png"
            imageSaver.save(bitmap, imageFileName,directory)
        }catch (e:Exception){
            throw e
        }
    }

    private fun deletePicture(imagePath:String){
        imageSaver.deleteFileByPath(imagePath)
    }

}