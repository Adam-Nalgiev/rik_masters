package ru.rikmasters.line_chart_api.domain

import ru.rikmasters.line_chart_api.data.LineChartRepository
import ru.rikmasters.line_chart_api.entity.Statistics

interface GetStatisticUseCase {
    val repository: LineChartRepository

    suspend fun execute(): Statistics
}