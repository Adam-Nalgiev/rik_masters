package ru.rikmasters.circular_chart_api.domain

import ru.rikmasters.circular_chart_api.data.CircularChartRepositoryApi

interface GetStatisticUseCaseApi {
    val repository: CircularChartRepositoryApi

    fun execute()
}