package ru.rikmasters.circular_chart_impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.rikmasters.circular_chart_api.domain.GetUsersUseCaseApi
import ru.rikmasters.circular_chart_api.presentation.CircularChartViewModelApi

class CircularChartViewModel(
    override val getUsersUseCaseApi: GetUsersUseCaseApi
) : ViewModel(), CircularChartViewModelApi {

    private var _data = MutableStateFlow<Pair<Float, Float>>(Pair(0f, 0f))
    val data = _data.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        //getUsersUseCaseApi.execute()
    }

}