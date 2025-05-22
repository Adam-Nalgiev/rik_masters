package ru.rikmasters.line_chart_impl.data

import ru.rikmasters.line_chart_api.data.LineChartRepository
import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.StatisticsResponse

internal class LineChartRepository(
    override val client: NetworkClientApi
) : LineChartRepository {

    override suspend fun getStatsData(): StatisticsResponse {
        return client.getStatistic()
    }
}