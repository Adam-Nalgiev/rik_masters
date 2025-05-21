package ru.rikmasters.line_chart_impl.presentation.viewmodel

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import ru.rikmasters.line_chart_api.domain.GetStatisticUseCase
import ru.rikmasters.line_chart_api.presentation.LineChartViewModel
import ru.rikmasters.line_chart_impl.R
import kotlin.math.roundToInt

class LineChartViewModel(
    override val getStatisticUseCase: GetStatisticUseCase
) : ViewModel(), LineChartViewModel {
    // Холпер для определения ближайшей точки по X
    fun nearestPointIndex(tap: Offset, count: Int, canvasSize: Size): Int {
        val step = canvasSize.width / (count - 1)
        val idx = (tap.x / step).roundToInt().coerceIn(0, count - 1)
        return idx
    }

    //Изменим окончания для числительных
    fun wordFormatter(count: Int, context: Context): String {
        return context.resources.getQuantityString(R.plurals.visitor, count)
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
}