package com.vullpes.financeapp.presentation.charts

import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow

data class UiChartState(
    val loading:Boolean = false,
    val accountChart:Resource<Map<String, Double>> = Resource.Loading()
)
