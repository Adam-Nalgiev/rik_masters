package ru.rikmasters.viewers_list_api.domain

import ru.rikmasters.network_client_api.entity.StatisticsResponse
import ru.rikmasters.viewers_list_api.data.ViewersListRepositoryApi

interface GetStatisticUseCaseApi {
    val repository: ViewersListRepositoryApi

    suspend fun execute(): StatisticsResponse
}