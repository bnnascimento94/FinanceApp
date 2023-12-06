package com.vullpes.financeapp.data.sharedPreferences


import com.vullpes.financeapp.domain.model.User

interface PreferenciasRepository {

    fun saveUser(userID: Int)
    fun cleanData()
    fun getSavedUser(): Int?

}