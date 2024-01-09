package com.vullpes.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenciasRepositoryImpl @Inject constructor(
    private val preferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
): PreferenciasRepository {


    private val USER_ID = "userID"
    private val useBiometrics = "allowBiometrics"

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

    override fun allowUseBiometrics(allow: Boolean) {
        editor.putBoolean(useBiometrics, allow)
        editor.commit()
    }

    override fun getAllowBiometrics(): Boolean {
        return preferences.getBoolean(useBiometrics, false)
    }
}