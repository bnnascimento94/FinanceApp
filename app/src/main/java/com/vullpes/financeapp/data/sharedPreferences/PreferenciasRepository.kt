package com.vullpes.financeapp.data.sharedPreferences


interface PreferenciasRepository {

    fun saveUser(userID: Int)
    fun cleanData()
    fun getSavedUser(): Int?

}