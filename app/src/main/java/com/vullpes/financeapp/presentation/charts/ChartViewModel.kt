package com.vullpes.financeapp.presentation.charts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vullpes.financeapp.domain.util.toDate
import com.vullpes.financeapp.navigation.Constants
import com.vullpes.financeapp.presentation.authentication.register.UiStateRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    var uiState by mutableStateOf(UiChartState())
        private set


    init {
        uiState = uiState.copy(
            accountID = savedStateHandle.get<Int>(
                key = Constants.ACCOUNTID
            )?:0
        )
    }


    fun selectDates(startDate:LocalDate, endDate: LocalDate){
        uiState = uiState.copy(firstDate = startDate.toDate(), secondDate =endDate.toDate())
    }

}