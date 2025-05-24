package ru.rikmasters.line_chart_impl.data

import ru.rikmasters.line_chart_api.data.LineChartRepositoryApi
import ru.rikmasters.network_client_api.NetworkClientApi
import ru.rikmasters.network_client_api.entity.StatisticsResponse

/**Хотел представить его, как самостоятельный компонент,
 * имеющий свой API, что позволило бы интегрировать его в другие места в приложении
 */
internal class LineChartRepository(
    override val client: NetworkClientApi
) : LineChartRepositoryApi {

    override suspend fun getStatsData(): StatisticsResponse {
        return client.getStatistic()
    }
}