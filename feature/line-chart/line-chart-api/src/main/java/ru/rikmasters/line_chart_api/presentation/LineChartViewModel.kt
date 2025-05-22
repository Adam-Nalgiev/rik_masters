package ru.rikmasters.line_chart_api.presentation

import ru.rikmasters.line_chart_api.domain.GetStatisticUseCase

interface LineChartViewModel {
    val getStatisticUseCase: GetStatisticUseCase
}