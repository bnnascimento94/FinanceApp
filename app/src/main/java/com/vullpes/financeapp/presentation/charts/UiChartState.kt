package com.vullpes.financeapp.presentation.charts

import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.piechart.models.PieChartData
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

data class UiChartState(
    val accountID: Int = 0,
    val loading:Boolean = false,
    val firstDate: Date? = null,
    val secondDate: Date? = null,
    val balanceAccount:List<Point>? = null,
    val groupAccount:PieChartData? = null,
    val categoryExpenses:List<BarData>? = null
)
