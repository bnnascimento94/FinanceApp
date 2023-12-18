package com.vullpes.financeapp.data.dataSource.firebase.auth

import co.yml.charts.common.extensions.isNotNull
import com.google.firebase.auth.FirebaseAuth
import com.vullpes.financeapp.data.dataSource.firebase.entities.UserFirebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthFirebaseDataSourceImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthFirebaseDataSource {
    override suspend fun buscarLogin(username: String, password: String): Boolean {
        return try{
            val data = firebaseAuth.signInWithEmailAndPassword(username,password).await()
            return data.user.isNotNull()
        }catch (e : Exception){
            throw e
        }
    }

    override suspend fun forgotPassword(email: String): Boolean {
        return try {
            return firebaseAuth.sendPasswordResetEmail(email).isSuccessful
        } catch (e: Exception) {
            throw e
        }
    }



    override suspend fun registerUser(username: String?, password: String?): Boolean {
        return try{
            val data = firebaseAuth.createUserWithEmailAndPassword(username!!,password!!).await()
            return data.user.isNotNull()
        }catch (e : Exception){
            throw e
        }
    }

    override suspend fun logoutUser() {
        try{
            val currentUser = firebaseAuth.currentUser
            if(currentUser != null){
                firebaseAuth.signOut()
            }
        }catch (e : Exception){
            throw e
        }
    }
}