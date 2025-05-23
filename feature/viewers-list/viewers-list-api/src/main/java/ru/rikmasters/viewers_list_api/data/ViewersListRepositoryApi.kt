package ru.rikmasters.viewers_list_api.data

import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.UsersResponse

interface ViewersListRepositoryApi {
    val client: NetworkClientApi

    suspend fun getUsers(): UsersResponse
}