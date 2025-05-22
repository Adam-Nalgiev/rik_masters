package ru.rikmasters.circular_chart_api.presentation

import ru.rikmasters.circular_chart_api.domain.GetStatisticUseCaseApi

interface CircularChartViewModelApi {
    val getStatisticUseCaseApi: GetStatisticUseCaseApi
}