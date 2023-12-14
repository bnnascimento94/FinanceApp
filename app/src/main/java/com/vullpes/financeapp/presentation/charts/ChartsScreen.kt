package com.vullpes.financeapp.presentation.charts

import android.annotation.SuppressLint
import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.components.Legends
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.barchart.models.BarStyle
import co.yml.charts.ui.barchart.models.SelectionHighlightData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import co.yml.charts.ui.piechart.utils.proportion
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.vullpes.financeapp.domain.util.toStringDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChartsScreen(
    uiState: UiChartState,
    onBackPressed: () -> Unit,
    onSelectedDates:(LocalDate, LocalDate) -> Unit
) {

    val dateDialog = rememberSheetState()
    var chartWidth by remember { mutableStateOf(0.dp) }
    var localDensity = LocalDensity.current
    Scaffold(
        topBar = {
            ChartTopAppBar(
                onBackPressed = onBackPressed,
                openCalendarDialog = { dateDialog.show() })
        }
    ) {
        Column(modifier = Modifier.padding(paddingValues = it).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            if(uiState.balanceAccount == null && uiState.categoryExpenses == null && uiState.groupAccount == null){
                Text(text = "Charts", style = MaterialTheme.typography.titleLarge)
                Text(text = "No Data available", style = MaterialTheme.typography.titleMedium)
            }else{
                LazyColumn(modifier = Modifier
                    .padding(6.dp)
                    .onGloballyPositioned { coordinates ->
                        chartWidth = with(localDensity) { coordinates.size.height.toDp() }
                    }
                    .fillMaxSize()) {
                    uiState.balanceAccount?.let {
                        item {
                            Text(
                                modifier=Modifier.padding(12.dp),
                                text = "Day balance from ${uiState.firstDate?.toStringDate()?:""} to ${uiState.secondDate?.toStringDate()?:""}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            DayBalanceLinechart(it, chartWidth)
                        }
                    }

                    uiState.groupAccount?.let {
                        item{
                            Text(
                                modifier=Modifier.padding(12.dp),
                                text = "Division by Group from ${uiState.firstDate?.toStringDate()?:""} to ${uiState.secondDate?.toStringDate()?:""}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            SimpleDonutChart(it)
                        }
                    }

                    uiState.categoryExpenses?.let {
                        item {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "Categories from ${uiState.firstDate?.toStringDate() ?: ""} to ${uiState.secondDate?.toStringDate() ?: ""}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            BarchartWithSolidBars(it)
                        }
                    }
                }
            }


        }

    }
    CalendarDialog(
        state = dateDialog,
        selection = CalendarSelection.Period { startDate, endDate ->
            onSelectedDates(startDate,endDate)
        },
        config = CalendarConfig(monthSelection = true, yearSelection = true)
    )
}



@Composable
fun DayBalanceLinechart(pointsData: List<Point>, widthChart: Dp) {
    val xAxisData = AxisData.Builder()
        .axisStepSize(60.dp)
        .steps(pointsData.size - 1)
        //.labelData { i -> i.toString() }
        .axisLabelAngle(20f)
        .labelAndAxisLinePadding(30.dp)
        .axisLabelColor(Color.Blue)
        .axisLineColor(Color.Transparent)
        .typeFace(Typeface.DEFAULT_BOLD)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(pointsData.size - 1)
        //.labelData { i ->
           // val orderedList = pointsData.sortedBy { it.y }
           // "${orderedList[i].y} k"
       // }
        .labelAndAxisLinePadding(30.dp)
        .axisLabelColor(Color.Blue)
        .axisLineColor(Color.Transparent)
        .typeFace(Typeface.DEFAULT_BOLD)
        .build()
    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    shadowUnderLine = ShadowUnderLine(),
                    lineStyle = LineStyle(lineType = LineType.SmoothCurve(), color = Color.Blue),
                    intersectionPoint = IntersectionPoint(color = Color.Red),
                    selectionHighlightPopUp = SelectionHighlightPopUp(
                        labelAlignment = Paint.Align.LEFT,
                        popUpLabel = { x, y ->
                        val pointSelected = pointsData.find { it.x == x}
                        val xLabel = pointSelected?.description
                        val yLabel = String.format("%.2f", y)
                        "$xLabel  $$yLabel"
                    })
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData
    )

    LineChart(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = data
    )
}


@Composable
private fun SimpleDonutChart(data:PieChartData) {

    // Sum of all the values
    val sumOfValues = data.totalLength

    // Calculate each proportion value
    val proportions = data.slices.proportion(sumOfValues)
    val pieChartConfig =
        PieChartConfig(
            labelVisible = true,
            strokeWidth = 120f,
            labelColor = Color.Black,
            activeSliceAlpha = .9f,
            isEllipsizeEnabled = true,
            labelTypeface = Typeface.defaultFromStyle(Typeface.BOLD),
            isAnimationEnable = true,
            chartPadding = 25,
            labelFontSize = 42.sp,
        )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        Legends(legendsConfig = DataUtils.getLegendsConfigFromPieChartData(pieChartData = data, 3))
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            data,
            pieChartConfig
        ) { slice ->
           // Toast.makeText(context, slice.label, Toast.LENGTH_SHORT).show()
        }
    }
}


@Composable
private fun BarchartWithSolidBars(barData:List<BarData>) {
    val maxRange = 50
    val yStepSize = barData.size + 15

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .startDrawPadding(48.dp)
        .labelData { index ->"${barData[index].label.subSequence(0,(barData[index].label.length/2))}..."  }
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        //.labelData { index -> (index * (maxRange / yStepSize)).toString() }
        .build()
    val barChartData = BarChartData(
        chartData = barData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        barStyle = BarStyle(
            paddingBetweenBars = 50.dp,
            barWidth = 25.dp,
            selectionHighlightData = SelectionHighlightData(
                highlightTextOffset =6.dp,
                popUpLabel = { x, y ->
                    val barSelected = barData.find { it.point.x == x }
                    val xLabel = barSelected?.label?:""
                    val yLabel = " ${String.format("%.2f", y)}"
                    "$xLabel $$yLabel"
                }
            )

        ),
        showYAxis = false,
        showXAxis = true,
        horizontalExtraSpace = 20.dp,
    )
    BarChart(modifier = Modifier.height(400.dp), barChartData = barChartData)
}

@Preview
@Composable
fun ChartPrev() {
    ChartsScreen(uiState = UiChartState(), onSelectedDates = { startDate, endDate ->}, onBackPressed = {}
    )
}