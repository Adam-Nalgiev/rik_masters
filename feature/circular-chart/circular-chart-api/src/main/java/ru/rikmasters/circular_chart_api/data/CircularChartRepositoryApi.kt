package ru.rikmasters.circular_chart_api.data

import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.UsersResponse

interface CircularChartRepositoryApi {
    val client: NetworkClientApi

    suspend fun getUsers(): UsersResponse
}