package com.vullpes.financeapp.presentation.profile

import com.vullpes.financeapp.domain.model.User

data class UiStateProfile(
    val profile: User? = null,
    val loading:Boolean = false
)