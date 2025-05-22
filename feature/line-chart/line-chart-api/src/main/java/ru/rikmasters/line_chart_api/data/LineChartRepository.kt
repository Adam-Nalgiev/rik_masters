package ru.rikmasters.line_chart_api.data

import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.StatisticsResponse

interface LineChartRepository {

    val client: NetworkClientApi

    suspend fun getStatsData(): StatisticsResponse
}