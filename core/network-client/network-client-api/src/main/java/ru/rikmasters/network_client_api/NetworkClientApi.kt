package ru.rikmasters.network_client_api

import ru.rikmasters.network_client_api.entity.Statistic
import ru.rikmasters.network_client_api.entity.Users

interface NetworkClientApi {

    suspend fun getStatistic(): Statistic

    suspend fun getUsers(): Users
}