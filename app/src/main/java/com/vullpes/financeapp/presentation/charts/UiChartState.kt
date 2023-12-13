package com.vullpes.financeapp.presentation.charts

import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

data class UiChartState(
    val accountID: Int = 0,
    val loading:Boolean = false,
    val firstDate: Date? = null,
    val secondDate: Date? = null,
    val accountChart:Resource<Map<String, Double>> = Resource.Loading()
)
