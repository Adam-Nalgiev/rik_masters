package ru.rikmasters.line_chart_api.presentation

import ru.rikmasters.line_chart_api.domain.GetStatisticUseCaseApi

interface LineChartViewModelApi {
    val getStatisticUseCase: GetStatisticUseCaseApi
}