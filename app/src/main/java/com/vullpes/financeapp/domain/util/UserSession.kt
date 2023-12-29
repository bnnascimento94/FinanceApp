package com.vullpes.financeapp.domain.util

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object UserSession {

    private lateinit var userSession: Job

    private val sessionScope = CoroutineScope(Dispatchers.Main)


    suspend fun startSession(onLimitReached:() -> Unit) {
        if(::userSession.isInitialized){
            userSession.cancel()
        }
        userSession = sessionScope.launch {
            delay(120000)
            onLimitReached()
        }



    }

    fun stopSession()  {
        userSession.cancel()
    }


}