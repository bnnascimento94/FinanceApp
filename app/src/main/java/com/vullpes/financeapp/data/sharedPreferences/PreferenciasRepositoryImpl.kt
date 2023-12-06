package com.vullpes.financeapp.data.sharedPreferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenciasRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
): PreferenciasRepository {


    private val USER_ID = "userID"

    override fun saveUser(userID: Int) {
        editor.putInt(USER_ID, userID)
        editor.commit()
    }

    override fun cleanData() {
        editor.clear()
        editor.commit()
    }

    override fun getSavedUser(): Int{
        return preferences.getInt(USER_ID, 0)
    }
}