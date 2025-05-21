package ru.rikmasters.network_client_impl.client

import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.Statistic
import ru.rikmasters.network_client_api.entity.Users

object NetworkClient : NetworkClientApi {
    override suspend fun getStatistic(): Statistic {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(): Users {
        TODO("Not yet implemented")
    }
}