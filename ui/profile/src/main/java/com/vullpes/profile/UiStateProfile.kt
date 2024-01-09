package com.vullpes.profile

import com.vullpes.financeapp.authentication.domain.User

data class UiStateProfile(
    val profile: User? = null,
    val openImageDialog: Boolean = false,
    val checkBiometricSupport:Boolean = false,
    val biometricAuthpermission:Boolean = false,
    val loading:Boolean = false
)