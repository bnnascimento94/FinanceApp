package com.vullpes.financeapp.presentation.authentication.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ViewModelRegister(): ViewModel() {
    var uiState by mutableStateOf(UiStateRegister())
        private set
}