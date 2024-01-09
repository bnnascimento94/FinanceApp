package com.vullpes.firebase.entities

data class UserFirebase(
    val uid: String? = null,
    val displayName: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val isEmailVerified:Boolean? = null
)

