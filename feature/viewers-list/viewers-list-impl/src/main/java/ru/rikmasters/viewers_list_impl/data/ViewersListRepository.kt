package ru.rikmasters.viewers_list_impl.data

import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.UsersResponse
import ru.rikmasters.viewers_list_api.data.ViewersListRepositoryApi

class ViewersListRepository(
    override val client: NetworkClientApi
) : ViewersListRepositoryApi {

    override suspend fun getUsers(): UsersResponse {
        return client.getUsers()
    }
}