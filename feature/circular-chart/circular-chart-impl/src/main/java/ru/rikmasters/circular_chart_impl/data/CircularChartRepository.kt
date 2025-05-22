package ru.rikmasters.circular_chart_impl.data

import ru.rikmasters.circular_chart_api.data.CircularChartRepositoryApi
import ru.rikmasters.network_client_impl.client.NetworkClient

class CircularChartRepository(
    override val client: NetworkClient
) : CircularChartRepositoryApi {

    override fun getStatistic() {
        TODO("Not yet implemented")
    }
}