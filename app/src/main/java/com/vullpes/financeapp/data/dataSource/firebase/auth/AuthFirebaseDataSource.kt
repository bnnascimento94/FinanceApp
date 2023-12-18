package com.vullpes.financeapp.data.dataSource.firebase.auth

import com.vullpes.financeapp.data.dataSource.firebase.entities.UserFirebase

interface AuthFirebaseDataSource {

    suspend fun buscarLogin(username:String, password:String): Boolean

    suspend fun forgotPassword(username:String): Boolean

    suspend fun registerUser(username: String?, password: String?): Boolean

    suspend fun logoutUser()


}