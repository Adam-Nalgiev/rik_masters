package ru.rikmasters.network_client_api

import ru.rikmasters.network_client_api.entity.StatisticsResponse
import ru.rikmasters.network_client_api.entity.UsersResponse

interface NetworkClientApi {

    suspend fun getStatistic(): StatisticsResponse

    suspend fun getUsers(): UsersResponse
}