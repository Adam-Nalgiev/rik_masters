package ru.rikmasters.circular_chart_api.data

import ru.rikmasters.network_client_api.NetworkClientApi

interface CircularChartRepositoryApi {
    val client: NetworkClientApi

    fun getStatistic()
}