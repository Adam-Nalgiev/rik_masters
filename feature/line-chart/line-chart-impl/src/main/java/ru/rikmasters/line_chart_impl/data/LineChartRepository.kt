package ru.rikmasters.line_chart_impl.data

import ru.rikmasters.line_chart_api.data.LineChartRepository
import ru.rikmasters.network_client_api.entity.StatisticsResponse
import ru.rikmasters.network_client_impl.client.NetworkClient

/**Хотел представить его, как самостоятельный компонент,
 * имеющий свой API, что позволило бы интегрировать его в другие места в приложении
 */
internal class LineChartRepository(
    override val client: NetworkClient
) : LineChartRepository {

    override suspend fun getStatsData(): StatisticsResponse {
        return client.getStatistic()
    }
}