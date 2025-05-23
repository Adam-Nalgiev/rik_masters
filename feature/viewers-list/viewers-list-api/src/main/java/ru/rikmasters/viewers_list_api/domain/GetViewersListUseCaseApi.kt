package ru.rikmasters.viewers_list_api.domain

import ru.rikmasters.network_client_api.entity.UsersResponse
import ru.rikmasters.viewers_list_api.data.ViewersListRepositoryApi

interface GetViewersListUseCaseApi {
    val repository: ViewersListRepositoryApi

    suspend fun execute(): UsersResponse
}