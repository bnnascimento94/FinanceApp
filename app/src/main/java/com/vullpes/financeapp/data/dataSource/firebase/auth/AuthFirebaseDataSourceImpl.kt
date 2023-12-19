package com.vullpes.financeapp.data.dataSource.firebase.auth

import co.yml.charts.common.extensions.isNotNull
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
            firebaseAuth.sendPasswordResetEmail(email).await()
            return true
        } catch (e: Exception) {
            throw e
        }
    }



    override suspend fun registerUser(email: String?, password: String?): Boolean {
        return try{
            val data = firebaseAuth.createUserWithEmailAndPassword(email!!,password!!).await()
            return data.user.isNotNull()
        }catch (e : Exception){
            throw e
        }
    }

    override suspend fun updateEmail(email: String): Boolean {
        return try{
            val currentUser = firebaseAuth.currentUser
            currentUser?.verifyBeforeUpdateEmail(email)?.await()
            true
        }catch (e : Exception){
            throw e
        }
    }

    override suspend fun updatePassword(password: String): Boolean {
        return try{
            val currentUser = firebaseAuth.currentUser
            currentUser?.updatePassword(password)?.await()
            true
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