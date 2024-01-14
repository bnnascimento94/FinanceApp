package com.vullpes.charts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.piechart.models.PieChartData
import com.vullpes.charts.usecase.AccountBalanceByDateUseCase
import com.vullpes.charts.usecase.AllCategoryTransactionByAccountAndDateUseCase
import com.vullpes.charts.usecase.GroupTransactionsByTransferenceWithdrawalDepositUsecase
import com.vullpes.common.domain.dates.toDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val accountBalanceByDateUseCase: AccountBalanceByDateUseCase,
    private val groupTransactionsByTransferenceWithdrawalDepositUsecase: GroupTransactionsByTransferenceWithdrawalDepositUsecase,
    private val allCategoryTransactionByAccountAndDateUseCase: AllCategoryTransactionByAccountAndDateUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var uiState by mutableStateOf(UiChartState())
        private set


    init {
        val currentMonth: Map<String, Date> = com.vullpes.common.domain.dates.BegginingEndMonth.execute()
        uiState = uiState.copy(
            accountID = savedStateHandle.get<Int>(
                key = com.vullpes.common.navigation.Constants.ACCOUNTID
            ) ?: 0,
            firstDate = currentMonth["firstDate"],
            secondDate = currentMonth["lastDate"]
        )
        load()

    }

    private fun load() {
        getBalanceByDate()
        getGroupAccountByDate()
        categoryTransactionsByAccountAndDate()
    }


    fun selectDates(startDate: LocalDate, endDate: LocalDate) {
        uiState = uiState.copy(firstDate = startDate.toDate(), secondDate = endDate.toDate())
        getBalanceByDate()
        getGroupAccountByDate()
        categoryTransactionsByAccountAndDate()
    }

    private fun getBalanceByDate() = viewModelScope.launch {
        if (uiState.firstDate != null && uiState.secondDate != null) {
            accountBalanceByDateUseCase.invoke(
                uiState.accountID,
                uiState.firstDate!!,
                uiState.secondDate!!
            ).collect {
                var i: Int = 0
                val balancePoints = mutableListOf<Point>()
                it.forEach { balanceValues ->
                    balancePoints.add(
                        Point(
                            (++i).toFloat(),
                            balanceValues.value.toFloat(),
                            balanceValues.key
                        )
                    )
                }
                uiState = uiState.copy(balanceAccount = balancePoints.ifEmpty { null })
            }
        }

    }

    private fun getGroupAccountByDate() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main){
            if(uiState.accountID == 0 && uiState.firstDate == null && uiState.secondDate == null){
                val currentMonth: Map<String, Date> = com.vullpes.common.domain.dates.BegginingEndMonth.execute()
                uiState = uiState.copy(
                    accountID = savedStateHandle.get<Int>(
                        key = com.vullpes.common.navigation.Constants.ACCOUNTID
                    ) ?: 0,
                    firstDate = currentMonth["firstDate"],
                    secondDate = currentMonth["lastDate"]
                )
            }
        }
        val result = groupTransactionsByTransferenceWithdrawalDepositUsecase.execute(
            uiState.accountID,
            uiState.firstDate!!,
            uiState.secondDate!!
        ).map {
            PieChartData.Slice(
                it.key, it.value.toFloat(), Color(
                    Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                )
            )
        }
        withContext(Dispatchers.Main) {
            uiState = uiState.copy(
                groupAccount = if(result.isNotEmpty()) PieChartData(
                    slices = result,
                    plotType = PlotType.Donut
                ) else null
            )
        }

    }

    private fun categoryTransactionsByAccountAndDate() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main){
            if(uiState.accountID == 0 && uiState.firstDate == null && uiState.secondDate == null){
                val currentMonth: Map<String, Date> = com.vullpes.common.domain.dates.BegginingEndMonth.execute()
                uiState = uiState.copy(
                    accountID = savedStateHandle.get<Int>(
                        key = com.vullpes.common.navigation.Constants.ACCOUNTID
                    ) ?: 0,
                    firstDate = currentMonth["firstDate"],
                    secondDate = currentMonth["lastDate"]
                )
            }
        }
        var index = 0;
        val result = allCategoryTransactionByAccountAndDateUseCase.execute(
            uiState.accountID,
            uiState.firstDate!!,
            uiState.secondDate!!
        ).map {

            val point = Point(
                index.toFloat(),
                "%.2f".format(it.value).toFloat()
            )
            index++

            BarData(
                point = point,
                color = Color(
                    Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
                ),
                dataCategoryOptions = DataCategoryOptions(),
                label = it.key?:"Transferences",
            )

        }

        withContext(Dispatchers.Main) {
            uiState = uiState.copy(
                categoryExpenses = result.ifEmpty { null }
            )
        }
    }

}