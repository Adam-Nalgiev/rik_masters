package ru.rikmasters.line_chart_impl.domain

import ru.rikmasters.line_chart_api.data.LineChartRepository
import ru.rikmasters.line_chart_api.domain.GetStatisticUseCase
import ru.rikmasters.line_chart_api.entity.Statistics

internal class GetStatisticUseCase(
    override val repository: LineChartRepository
) : GetStatisticUseCase {
    override suspend fun execute(): Statistics {
        TODO("Not yet implemented")
    }
}