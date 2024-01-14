package com.vullpes.common.domain.imagem

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.os.Environment
import android.util.Base64
import android.util.Log
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ImageSaverImpl @Inject constructor(private val context: Context) : ImageSaver {

    override fun save(bitmapImage: Bitmap,directoryName:String, fileName:String ): String? {
        var fileOutputStream: FileOutputStream? = null
        var filePath: String? = null
        try {
            val file = createFile(directoryName, fileName)
            filePath = file.absolutePath
            fileOutputStream = FileOutputStream(file)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fileOutputStream?.close()
            } catch (e: IOException) {
               e.printStackTrace()
            }
        }
        return filePath
    }

    override fun saveJPEG(bitmapImage: Bitmap,directoryName:String, fileName:String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(createFile(directoryName, fileName))
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fileOutputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun createFile(directoryName:String, fileName:String): File {
        val directory =
            File(context.getExternalFilesDir(null)!!.absolutePath + "/" + directoryName)
        if (!directory.exists() && !directory.mkdirs()) {
            Log.e("ImageSaver", "Error creating directory $directory")
        }
        return File(directory, fileName)
    }

    override fun load(directoryName:String, fileName:String): Bitmap? {
        var inputStream: FileInputStream? = null
        try {
            inputStream = FileInputStream(createFile(directoryName, fileName))
            return BitmapFactory.decodeStream(inputStream)
            //return CarregadorDeFoto.carrega(createFile().getAbsolutePath());
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return null
    }

    override fun deleteFile(directoryName:String, fileName:String): Boolean {
        val deleted: Boolean
        val dir = File(Environment.getExternalStorageDirectory().toString() + "/" + directoryName)
        val f0 = File(dir, fileName)
        deleted = f0.delete()
        return deleted
    }

    override fun deleteFileByPath(path:String): Boolean {
        val deleted: Boolean
        val f0 = File(path)
        deleted = f0.delete()
        return deleted
    }

    override fun deleteDir(directoryName:String): Boolean {
        val dir = File(Environment.getExternalStorageDirectory().toString() + "/" + directoryName)
        deleteRecursive(dir)
        return true
    }

    override fun deleteRecursive(fileOrDirectory: File) {
        if (fileOrDirectory.isDirectory) {
            for (child in fileOrDirectory.listFiles()) {
                deleteRecursive(child)
            }
        }
        fileOrDirectory.delete()
    }

    override fun rotateImage(imagePath:String): Bitmap {
        var exifInterface: ExifInterface? = null
        var bitmap: Bitmap? = null
        try {
            bitmap = BitmapFactory.decodeFile(imagePath)
            exifInterface = ExifInterface(imagePath)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val orientation = exifInterface!!.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )
        val matrix = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.setRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.setRotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.setRotate(270f)
            else -> {}
        }
        return Bitmap.createBitmap(bitmap!!, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    override fun getFileQualityReduced(img: File): File? {
        var imgToSdcard: File? = null
        if (img.exists()) {
            //tamanho original
            try {
                val bit = decodeFile(img.absolutePath)
                val bos = ByteArrayOutputStream()
                bit!!.compress(Bitmap.CompressFormat.JPEG, 30 /*ignored for PNG*/, bos)
                val bitmapdata = bos.toByteArray()
                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val nomeImg = "IMAGE_" + timeStamp + "_"
                //File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                val storageDirectory = context.getExternalFilesDir(null)
                imgToSdcard = File.createTempFile(nomeImg, ".jpg", storageDirectory)
                val outputStream = FileOutputStream(imgToSdcard, false)
                outputStream.write(bitmapdata, 0, bitmapdata.size)
                outputStream.flush()
                outputStream.close()
            } catch (e: Exception) {
                Log.e("ERRO DE CONVERSAO", e.message!!)
            }
        }
        return imgToSdcard
    }

    override fun numberOfFilesDisplay(directoryName: String, context: Context): Int {
        val directory =
            File(context.getExternalFilesDir(null)!!.absolutePath + "/" + directoryName)
        return if (!directory.exists() && !directory.mkdirs()) {
            0
        } else {
            directory.listFiles().size
        }
    }

    override fun decodeFile(filePath: String?): Bitmap? {
        val o = BitmapFactory.Options()
        o.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, o)

// The new size we want to scale to
        val REQUIRED_SIZE = 1024

// Find the correct scale value. It should be the power of 2.
        var width_tmp = o.outWidth
        var height_tmp = o.outHeight
        var scale = 1
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) break
            width_tmp /= 2
            height_tmp /= 2
            scale *= 2
        }

// Decode with inSampleSize
        val o2 = BitmapFactory.Options()
        o2.inSampleSize = scale
        var image = BitmapFactory.decodeFile(filePath, o2)
        val exif: ExifInterface
        try {
            exif = ExifInterface(filePath!!)
            val exifOrientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            var rotate = 0
            when (exifOrientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
            }
            if (rotate != 0) {
                val w = image.width
                val h = image.height

                // Setting pre rotate
                val mtx = Matrix()
                mtx.preRotate(rotate.toFloat())

                // Rotating Bitmap & convert to ARGB_8888, required by tess
                image = Bitmap.createBitmap(image, 0, 0, w, h, mtx, false)
            }
        } catch (e: IOException) {
            return null
        }
        return image.copy(Bitmap.Config.ARGB_8888, true)
    }

    override fun getDominantColor(bitmap: Bitmap?): Int {
        val newBitmap = Bitmap.createScaledBitmap(bitmap!!, 1, 1, true)
        val color = newBitmap.getPixel(0, 0)
        newBitmap.recycle()
        return color
    }

    override fun createImageFile(context: Context): File? {
        var image: File? = null
        try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val imageFileName = "IMAGE_" + timeStamp + "_"
            val storageDirectory = context.getExternalFilesDir(null)
            image = File.createTempFile(imageFileName, ".jpg", storageDirectory)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }

    override fun convertUrlToBitmap(url: String): Bitmap?{
        val bmOptions = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeFile(url, bmOptions)
        return bitmap
    }

    override fun pathToBase64(path: String?): String? {
        val bitmap = compressImage(path)

        return getBase64Bitmap(bitmap)?.replace("\n", "")
    }

    override fun getBase64String(file: File): String? {
        val bitmap = BitmapFactory.decodeFile(file.path)
        return if (bitmap != null) {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        } else {
            throw Exception("Bitmap Nulo")
        }
    }

    override fun getBase64Bitmap(bitmap: Bitmap?): String? {
        return bitmap?.let {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            Base64.encodeToString(byteArray, Base64.DEFAULT)
        }
            ?: throw Exception("Bitmap Nulo")
    }

    override fun compressImage(path: String?): Bitmap? {
        val image = BitmapFactory.decodeFile(path)
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos) // 100baos
        var options = 100
        while (baos.toByteArray().size / 1024 > 100 && options >= 0) { // 100kb,
            baos.reset() // baosbaos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos) // options%baos
            options -= 10 // 10
        }
        val isBm = ByteArrayInputStream(
            baos.toByteArray()
        ) // baosByteArrayInputStream
        return BitmapFactory.decodeStream(isBm, null, null)
    }

    override fun compressImageBitmap(bitmap: Bitmap): Bitmap? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos) // 100baos
        var options = 100
        while (baos.toByteArray().size / 1024 > 100 && options >= 0) { // 100kb,
            baos.reset() // baosbaos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos) // options%baos
            options -= 10 // 10
        }
        val isBm = ByteArrayInputStream(
            baos.toByteArray()
        ) // baosByteArrayInputStream
        return BitmapFactory.decodeStream(isBm, null, null)
    }

    override fun bitmapToFile(context: Context, bitmap: Bitmap, fileName: String): File? {
        var file: File? = null
        try {
            file = File.createTempFile(fileName, null, context.cacheDir)
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

}