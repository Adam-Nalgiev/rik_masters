package ru.rikmasters.line_chart_impl.domain

import ru.rikmasters.line_chart_api.domain.GetStatisticUseCase
import ru.rikmasters.line_chart_impl.data.LineChartRepository
import ru.rikmasters.network_client_api.entity.StatisticsResponse

internal class GetStatisticUseCase(
    override val repository: LineChartRepository
) : GetStatisticUseCase {
    override suspend fun execute(): StatisticsResponse {
        return repository.getStatsData()
    }
}