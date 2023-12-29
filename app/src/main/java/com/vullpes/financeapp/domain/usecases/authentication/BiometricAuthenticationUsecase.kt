package com.vullpes.financeapp.domain.usecases.authentication

import android.Manifest
import android.annotation.SuppressLint
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import javax.inject.Inject

class BiometricAuthenticationUsecase @Inject constructor( private val context: Context) {

    private var cancellationSignal: CancellationSignal? = null

    @SuppressLint("NewApi")
    fun execute(onSucess:() -> Unit, onError:(String) -> Unit){

        if (checkBiometricSupport()) {
            val authenticationCallback: BiometricPrompt.AuthenticationCallback =
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                        super.onAuthenticationSucceeded(result)
                        onSucess()
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                        super.onAuthenticationError(errorCode, errString)
                        onError(errString.toString())
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        onError("Authentication Error")
                    }

                    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                        super.onAuthenticationHelp(helpCode, helpString)
                        onError(helpString.toString())
                    }
                }


            val biometricPrompt = BiometricPrompt.Builder(context)
                .apply {
                    setTitle("Biometrics Auth")
                    setNegativeButton("Exit", context.mainExecutor) { _, _, ->
                        onError("")
                    }
                }.build()

            biometricPrompt.authenticate(getCancellationSignal(onCancelled = { onError("Cancelled")}), context.mainExecutor, authenticationCallback)
        }



    }

    private fun checkBiometricSupport(): Boolean {

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




    private fun getCancellationSignal(onCancelled:() -> Unit): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            onCancelled()
        }

        return cancellationSignal as CancellationSignal
    }
}