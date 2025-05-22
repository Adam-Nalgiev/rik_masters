package ru.rikmasters.circular_chart_impl.domain

import ru.rikmasters.circular_chart_api.domain.GetStatisticUseCaseApi
import ru.rikmasters.circular_chart_impl.data.CircularChartRepository

class GetStatisticUseCase(
    override val repository: CircularChartRepository
) : GetStatisticUseCaseApi {

    override fun execute() {
        TODO("Not yet implemented")
    }
}