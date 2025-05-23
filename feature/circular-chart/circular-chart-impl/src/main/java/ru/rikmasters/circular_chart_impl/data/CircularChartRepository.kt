package ru.rikmasters.circular_chart_impl.data

import ru.rikmasters.circular_chart_api.data.CircularChartRepositoryApi
import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.UsersResponse

class CircularChartRepository(
    override val client: NetworkClientApi
) : CircularChartRepositoryApi {

    override suspend fun getUsers(): UsersResponse {
        return client.getUsers()
    }
}