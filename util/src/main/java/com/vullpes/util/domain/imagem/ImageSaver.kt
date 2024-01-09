package com.vullpes.util.domain.imagem

import android.content.Context
import android.graphics.Bitmap
import java.io.File

interface ImageSaver {
    fun save(bitmapImage: Bitmap,directoryName:String, fileName:String): String?
    fun saveJPEG(bitmapImage: Bitmap,directoryName:String, fileName:String)
    fun createFile(directoryName:String, fileName:String): File
    fun load(directoryName:String, fileName:String): Bitmap?
    fun deleteFile(directoryName:String, fileName:String): Boolean
    fun deleteFileByPath(path: String): Boolean
    fun deleteDir(directoryName:String): Boolean
    fun deleteRecursive(fileOrDirectory: File)
    fun rotateImage(imagePath:String): Bitmap
    fun getFileQualityReduced(img: File): File?
    fun numberOfFilesDisplay(directoryName: String, context: Context): Int
    fun decodeFile(filePath: String?): Bitmap?
    fun getDominantColor(bitmap: Bitmap?): Int
    fun createImageFile(context: Context): File?
    fun convertUrlToBitmap(url: String): Bitmap?
    fun pathToBase64(path: String?): String?
    fun getBase64String(file: File): String?
    fun getBase64Bitmap(bitmap: Bitmap?): String?
    fun compressImage(path: String?): Bitmap?

    fun compressImageBitmap(bitmap: Bitmap): Bitmap?
    fun bitmapToFile(context: Context, bitmap: Bitmap, fileName: String): File?
}