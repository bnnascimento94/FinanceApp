package com.vullpes.common.domain.biometrics

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import javax.inject.Inject

class CheckBiometricSupportUsecase @Inject constructor(private val context: Context) {

    fun execute():Boolean{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){

            val keyGuardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

            if (!keyGuardManager.isDeviceSecure) {
                return true
            }

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
                return false
            }

            return context.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)

        }else{
            return false
        }
    }


}