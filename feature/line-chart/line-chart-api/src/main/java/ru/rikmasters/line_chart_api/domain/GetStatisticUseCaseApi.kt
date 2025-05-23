package ru.rikmasters.line_chart_api.domain

import ru.rikmasters.line_chart_api.data.LineChartRepositoryApi
import ru.rikmasters.network_client_api.entity.StatisticsResponse

interface GetStatisticUseCaseApi {
    val repository: LineChartRepositoryApi

    suspend fun execute(): StatisticsResponse
}