package com.vullpes.financeapp.domain.usecases.authentication

import android.graphics.Bitmap
import com.itm.juipdv.util.imagem.ImageSaver
import com.vullpes.financeapp.domain.UserRepository
import java.util.UUID
import javax.inject.Inject

class UpdatePhotoImageUsecase @Inject constructor(
    private val userRepository: UserRepository,
    private val imageSaver: ImageSaver
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