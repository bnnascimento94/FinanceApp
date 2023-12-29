package com.vullpes.financeapp.presentation.profile

import com.vullpes.financeapp.domain.model.User

data class UiStateProfile(
    val profile: User? = null,
    val openImageDialog: Boolean = false,
    val checkBiometricSupport:Boolean = false,
    val biometricAuthpermission:Boolean = false,
    val loading:Boolean = false
)