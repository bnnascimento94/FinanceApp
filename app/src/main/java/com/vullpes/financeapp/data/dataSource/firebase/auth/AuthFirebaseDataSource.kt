package com.vullpes.financeapp.data.dataSource.firebase.auth

interface AuthFirebaseDataSource {

    suspend fun buscarLogin(username:String, password:String): Boolean

    suspend fun forgotPassword(username:String): Boolean

    suspend fun registerUser(email: String?, password: String?): Boolean

    suspend fun updateEmail(email: String): Boolean

    suspend fun updatePassword(password: String): Boolean

    suspend fun logoutUser()


}