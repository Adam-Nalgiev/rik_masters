package ru.rikmasters.line_chart_impl.presentation.viewmodel

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.rikmasters.line_chart_api.domain.GetStatisticUseCaseApi
import ru.rikmasters.line_chart_api.presentation.LineChartViewModelApi
import ru.rikmasters.line_chart_impl.R
import ru.rikmasters.network_client_api.entity.StatisticsResponse
import kotlin.math.roundToInt

internal class LineChartViewModelImpl(
    override val getStatisticUseCase: GetStatisticUseCaseApi
) : ViewModel(), LineChartViewModelApi {

    private var _dataFlow = MutableStateFlow<List<Pair<String, Int>>>(emptyList())
    val dataFlow = _dataFlow.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataFlow.value = prepareData(getStatisticUseCase.execute())
        }
    }

    // Холпер для определения ближайшей точки по X
    fun nearestPointIndex(tap: Offset, count: Int, canvasSize: Size): Int {
        val step = canvasSize.width / (count - 1)
        val idx = (tap.x / step).roundToInt().coerceIn(0, count - 1)
        return idx
    }

    //Изменим окончания для числительных
    fun wordFormatter(count: Int, context: Context): String {
        return context.resources.getQuantityString(R.plurals.visitor, count, count)
    }

    //Форматируем дату в читаемый вид
    fun dateFormatter(date: String, context: Context): String {
        //первые два числа переменчивы в разрядности, поэтому отсчет с конца
        val monthNum =
            date.substring(startIndex = date.lastIndex - 5, endIndex = date.lastIndex - 3).toInt()
        val monthWord = context.resources.getStringArray(R.array.months)[monthNum - 1]

        val day = date.substring(startIndex = 0, endIndex = date.lastIndex - 5)

        return "$day $monthWord"
    }

    fun dateFormatter(date: String): String {
        //первые два числа переменчивы в разрядности, поэтому отсчет с конца
        val monthNum =
            date.substring(startIndex = date.lastIndex - 5, endIndex = date.lastIndex - 3)

        var day = date.substring(startIndex = 0, endIndex = date.lastIndex - 5)

        if (day.length < 2) {
            day = "0$day"
        }

        return "$day.$monthNum"
    }

    private fun prepareData(data: StatisticsResponse): List<Pair<String, Int>> {
        val viewDates = mutableListOf<String>()

        data.statistics.forEach { it.dates.forEach { viewDates.add(it) } }

        val preparedData = mutableMapOf<String, Int>()

        viewDates.sortBy {
            if (it.length < 8) {
                it.substring(0, 1).toInt()
            } else {
                it.substring(0, 2).toInt()
            }
        }
        for (value in viewDates) {
            preparedData[value] = preparedData.getOrDefault(value, 0) + 1
        }
        return preparedData.map { (key, count) -> key to count }
    }
}