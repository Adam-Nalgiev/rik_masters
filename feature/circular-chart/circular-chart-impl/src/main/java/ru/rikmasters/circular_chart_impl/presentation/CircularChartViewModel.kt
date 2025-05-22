package ru.rikmasters.circular_chart_impl.presentation

import androidx.lifecycle.ViewModel
import ru.rikmasters.circular_chart_api.presentation.CircularChartViewModelApi
import ru.rikmasters.circular_chart_impl.domain.GetStatisticUseCase

class CircularChartViewModel(
    override val getStatisticUseCaseApi: GetStatisticUseCase
) : ViewModel(), CircularChartViewModelApi