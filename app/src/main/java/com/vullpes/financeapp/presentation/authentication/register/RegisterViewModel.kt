package com.vullpes.financeapp.presentation.authentication.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vullpes.financeapp.navigation.Constants.ACCOUNTID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    var uiState by mutableStateOf(UiStateRegister())
        private set

    init {
        val accountID = savedStateHandle.get<Int>(ACCOUNTID)?:0
    }
}