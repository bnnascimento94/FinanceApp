package com.vullpes.financeapp.presentation.profile

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.financeapp.domain.usecases.authentication.GetCurrentUserUsecase
import com.vullpes.financeapp.domain.usecases.authentication.UpdatePhotoImageUsecase
import com.vullpes.financeapp.domain.usecases.authentication.UpdateUserUsercase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val updateUserUsecase: UpdateUserUsercase,
    private val getCurrentUserUsecase: GetCurrentUserUsecase,
    private val updatePhotoImageUsecase: UpdatePhotoImageUsecase
) : ViewModel() {

    var uiState by mutableStateOf(UiStateProfile())
        private set

    private var capturedImageUri by mutableStateOf<Uri?>(null)


    init {
        loadLoggedUser()
    }

    private fun loadLoggedUser() = viewModelScope.launch {
        withContext(Dispatchers.Main) {
            uiState = uiState.copy(loading = true)
        }
        val result = getCurrentUserUsecase.execute()
        withContext(Dispatchers.Main) {
            uiState = uiState.copy(loading = false, profile = result)
        }
    }

    fun dialogImageOpen() {
        uiState = uiState.copy(openImageDialog = !uiState.openImageDialog)
    }

    fun setName(username: String) {
        uiState = uiState.copy(profile = uiState.profile?.copy(name = username))
    }

    fun setEmail(email: String) {
        uiState = uiState.copy(profile = uiState.profile?.copy(email = email))
    }

    fun setPassword(password: String) {
        uiState = uiState.copy(profile = uiState.profile?.copy(password = password))
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun setImageBitmap(uriImage: Uri? = null) = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main){
            uiState = uiState.copy(loading = true)
        }
        val bitmap = uriImage?.let {
            val source: ImageDecoder.Source = ImageDecoder.createSource(context.contentResolver, it)
            ImageDecoder.decodeBitmap(source)
        }?: kotlin.run {
            capturedImageUri?.let {
                val source: ImageDecoder.Source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
        }

        if (bitmap != null) {
            updatePhotoImageUsecase.execute(photo = bitmap, oldImage = uiState.profile?.imgSrc)
            loadLoggedUser()
        }
        withContext(Dispatchers.Main){
            uiState = uiState.copy(loading = false, openImageDialog = false)
        }
    }


    fun createImageUri(): Uri {
        val file = context.createImageFile()
        val uri = FileProvider.getUriForFile(
            Objects.requireNonNull(context),
            "com.vullpes.financeapp" + ".provider", file
        )
        capturedImageUri = uri
        return uri
    }

    fun onSave(onSuccess:() ->Unit, onError:(String) -> Unit) = viewModelScope.launch(Dispatchers.IO){
        try {
            withContext(Dispatchers.Main){
                uiState = uiState.copy(loading = true)
            }
            uiState.profile?.let {

                updateUserUsecase.execute(
                    userID = it.id,
                    username = it.name,
                    email = it.email,
                    password = it.password
                )
            }
            withContext(Dispatchers.Main){
                uiState = uiState.copy(loading = false)
                onSuccess()
            }
        }catch (e:Exception){
            withContext(Dispatchers.Main){
                onError(e.message.toString())
            }
        }

    }


}

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
}