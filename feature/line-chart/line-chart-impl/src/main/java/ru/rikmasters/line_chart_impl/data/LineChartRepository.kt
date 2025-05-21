package ru.rikmasters.line_chart_impl.data

import ru.rikmasters.line_chart_api.data.LineChartRepository
import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.Statistic

internal class LineChartRepository(
    override val client: NetworkClientApi
) : LineChartRepository {

    override suspend fun getStatsData(): Statistic {
        return client.getStatistic()
    }
}