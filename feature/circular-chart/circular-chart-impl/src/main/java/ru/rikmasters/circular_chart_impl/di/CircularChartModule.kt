package ru.rikmasters.circular_chart_impl.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.rikmasters.circular_chart_impl.data.CircularChartRepository
import ru.rikmasters.circular_chart_impl.domain.GetStatisticUseCase
import ru.rikmasters.circular_chart_impl.presentation.CircularChartViewModel

val circularChartModule = module {
    single { CircularChartRepository(get()) }
    factory { GetStatisticUseCase(get()) }
    viewModel { CircularChartViewModel(get()) }
}