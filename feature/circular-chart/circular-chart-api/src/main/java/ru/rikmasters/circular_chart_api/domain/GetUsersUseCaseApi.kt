package ru.rikmasters.circular_chart_api.domain

import ru.rikmasters.circular_chart_api.data.CircularChartRepositoryApi
import ru.rikmasters.network_client_api.entity.UsersResponse

interface GetUsersUseCaseApi {
    val repository: CircularChartRepositoryApi

    suspend fun execute(): UsersResponse
}