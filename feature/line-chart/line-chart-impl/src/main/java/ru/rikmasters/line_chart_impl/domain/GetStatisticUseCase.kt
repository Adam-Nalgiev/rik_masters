package ru.rikmasters.line_chart_impl.domain

import ru.rikmasters.line_chart_api.data.LineChartRepositoryApi
import ru.rikmasters.line_chart_api.domain.GetStatisticUseCaseApi
import ru.rikmasters.network_client_api.entity.StatisticsResponse

internal class GetStatisticUseCase(
    override val repository: LineChartRepositoryApi
) : GetStatisticUseCaseApi {
    override suspend fun execute(): StatisticsResponse {
        return repository.getStatsData()
    }
}