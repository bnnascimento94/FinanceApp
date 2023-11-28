package com.vullpes.financeapp.presentation.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ViewModelLogin: ViewModel() {
    var uiState by mutableStateOf(UiStateLogin())
        private set
}