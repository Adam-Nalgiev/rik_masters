package ru.rikmasters.network_client_impl.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.InternalSerializationApi
import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.StatisticsResponse
import ru.rikmasters.network_client_api.entity.UsersResponse
import ru.rikmasters.network_client_impl.dto.StatisticsResponseDto
import ru.rikmasters.network_client_impl.dto.UsersResponseDto

/**Поскольку всего 2 запроса общих для всех модулей решил сделать из него отдельный общий (core) модуль.
 * В ином случае у каждого модуля был бы свой Клиент*/
@OptIn(InternalSerializationApi::class)
object NetworkClient : NetworkClientApi {
    private val client = HttpClient(CIO) {
        install(Logging)
        install(ContentNegotiation) {
            json()
        }
    }

    /** Так как результат одинаковый и постоянный для всех, решил сделать небольшое кеширование, чтобы избежать лишних запросов.
     * Очевидно, в продакшене лучше так не оставлять. Хотя изменять придётся скорее архитектурный подход, чтобы избежать повторения
     * однотиных запросов с постоянными результатами.
     */
    private var statResult: StatisticsResponse = StatisticsResponseDto(emptyList())

    private var usersResult: UsersResponse = UsersResponseDto(emptyList())

    override suspend fun getStatistic(): StatisticsResponse {
        return if (statResult.statistics.isEmpty()) {
            val response: StatisticsResponseDto = client.get(BASE_URL + GET_STATISTIC_PATH).body()
            statResult = response
            response
        } else {
            statResult
        }
    }

    override suspend fun getUsers(): UsersResponse {
        return if (usersResult.users.isEmpty()) {
            val response: UsersResponseDto = client.get(BASE_URL + GET_USERS_PATH).body()
            usersResult = response
            response
        } else {
            usersResult
        }
    }

    private const val BASE_URL = "http://test.rikmasters.ru/"

    private const val GET_STATISTIC_PATH = "api/statistics/"
    private const val GET_USERS_PATH = "api/users/"

}