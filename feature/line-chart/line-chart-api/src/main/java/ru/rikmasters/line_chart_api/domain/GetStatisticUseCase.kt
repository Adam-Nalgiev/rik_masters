package ru.rikmasters.line_chart_api.domain

import ru.rikmasters.line_chart_api.data.LineChartRepository
import ru.rikmasters.network_client_api.entity.StatisticsResponse

interface GetStatisticUseCase {
    val repository: LineChartRepository

    suspend fun execute(): StatisticsResponse
}