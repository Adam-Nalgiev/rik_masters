package ru.rikmasters.line_chart_api.data

import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.Statistic

interface LineChartRepository {

    val client: NetworkClientApi

    suspend fun getStatsData(): Statistic
}