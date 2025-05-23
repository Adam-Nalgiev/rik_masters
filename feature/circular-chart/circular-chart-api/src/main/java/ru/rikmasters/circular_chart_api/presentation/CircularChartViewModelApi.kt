package ru.rikmasters.circular_chart_api.presentation

import ru.rikmasters.circular_chart_api.domain.GetUsersUseCaseApi

interface CircularChartViewModelApi {
    val getUsersUseCaseApi: GetUsersUseCaseApi
}