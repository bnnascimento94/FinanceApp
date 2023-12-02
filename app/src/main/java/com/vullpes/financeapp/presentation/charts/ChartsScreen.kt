package com.vullpes.financeapp.presentation.charts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChartsScreen(
    uiState: UiChartState,
    onBackPressed: () -> Unit
) {

    Scaffold(
        topBar = {
            ChartTopAppBar(onBackPressed = onBackPressed)
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1f)) {

            }
            Column(modifier = Modifier.weight(1f)) {
                LazyColumn{

                }
            }
        }

    }
}