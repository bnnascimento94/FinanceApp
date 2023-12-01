package com.vullpes.financeapp.presentation.charts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.vullpes.financeapp.presentation.authentication.register.UiStateRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(): ViewModel() {
    var uiState by mutableStateOf(UiChartState())
        private set
}