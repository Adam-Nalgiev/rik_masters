package ru.rikmasters.line_chart_impl.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.rikmasters.line_chart_api.data.LineChartRepositoryApi
import ru.rikmasters.line_chart_api.domain.GetStatisticUseCaseApi
import ru.rikmasters.line_chart_impl.data.LineChartRepository
import ru.rikmasters.line_chart_impl.domain.GetStatisticUseCase
import ru.rikmasters.line_chart_impl.presentation.viewmodel.LineChartViewModelImpl

val lineChartModule = module {
    single { LineChartRepository(get()) } bind LineChartRepositoryApi::class
    factory { GetStatisticUseCase(get()) } bind GetStatisticUseCaseApi::class
    viewModel { LineChartViewModelImpl(get()) }
}