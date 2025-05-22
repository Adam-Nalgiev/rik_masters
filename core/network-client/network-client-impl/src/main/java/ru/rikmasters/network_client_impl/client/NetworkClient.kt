package ru.rikmasters.network_client_impl.client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.StatisticsResponse
import ru.rikmasters.network_client_api.entity.UsersResponse
import ru.rikmasters.network_client_impl.dto.StatisticsResponseDto
import ru.rikmasters.network_client_impl.dto.UsersResponseDto

object NetworkClient : NetworkClientApi {
    private val client = HttpClient(CIO) {
        install(Logging)
        install(ContentNegotiation) {
            json()
        }
    }

    override suspend fun getStatistic(): StatisticsResponse {
        val response: StatisticsResponseDto = client.get(BASE_URL + GET_STATISTIC_PATH).body()
        Log.d("RESPONSE STATS", "$response")
        return response
    }

    override suspend fun getUsers(): UsersResponse {
        val response: UsersResponseDto = client.get(BASE_URL + GET_USERS_PATH).body()
        Log.d("RESPONSE USERS", "$response")
        return response
    }

    private const val BASE_URL = "http://test.rikmasters.ru/"

    private const val GET_STATISTIC_PATH = "api/statistics/"
    private const val GET_USERS_PATH = "api/users/"
}