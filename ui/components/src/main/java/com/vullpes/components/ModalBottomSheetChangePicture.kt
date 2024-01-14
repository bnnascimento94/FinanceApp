package com.vullpes.components

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.vullpes.common.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetChangePicture(
    onDismiss: (SheetState) -> Unit,
    onAddImageFromGallery:(SheetState,Uri) -> Unit,
    onAddImage: (SheetState) -> Unit,
    createUri:() -> Uri
) {

    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss(modalBottomSheetState) },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        SelectPicture(onAddImage = {onAddImage(modalBottomSheetState)}, createUri = createUri, onAddImageFromGallery = {onAddImageFromGallery(modalBottomSheetState, it)})
    }

}


@Composable
fun SelectPicture(
    onAddImageFromGallery:(Uri) -> Unit,
    onAddImage: () -> Unit,
    createUri:() -> Uri
) {

    val context = LocalContext.current

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            onAddImageFromGallery(uri)
        } else {
            Toast.makeText(context,"No media selected", Toast.LENGTH_SHORT).show()
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        onAddImage()
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            cameraLauncher.launch(createUri())
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val permissions = arrayOf(
        Manifest.permission.CAMERA
    )



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier
                .clickable {
                    if (permissions.all {
                            ContextCompat.checkSelfPermission(
                                context,
                                it
                            ) == PackageManager.PERMISSION_GRANTED
                        }) {
                        cameraLauncher.launch(createUri())
                    } else {
                        launcherMultiplePermissions.launch(permissions)
                    }

                },
                verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surface) {
                    Icon(
                        Icons.Default.CameraAlt,
                        contentDescription = stringResource(R.string.camera_icon)
                    )
                }
                Text(modifier = Modifier.padding(start = 6.dp),text = "Take Photo")
            }
            Icon(
                Icons.Default.ArrowForwardIos,
                contentDescription = stringResource(R.string.arrow_foward)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.surface) {
                    Icon(
                        Icons.Default.Image,
                        contentDescription = stringResource(R.string.camera_icon)
                    )
                }

                Text(modifier = Modifier.padding(start = 6.dp), text = "Choose From Gallery")
            }
            Icon(
                Icons.Default.ArrowForwardIos,
                contentDescription = stringResource(R.string.arrow_foward)
            )
        }


    }

}

@Preview(showBackground = true)
@Composable
fun SelectPicturePreview() {
    SelectPicture(onAddImage = {}, createUri = {"www.google.com".toUri()}, onAddImageFromGallery = {})
}

