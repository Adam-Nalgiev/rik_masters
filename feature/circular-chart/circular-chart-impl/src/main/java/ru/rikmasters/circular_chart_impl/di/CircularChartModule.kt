package ru.rikmasters.circular_chart_impl.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.rikmasters.circular_chart_api.data.CircularChartRepositoryApi
import ru.rikmasters.circular_chart_api.domain.GetUsersUseCaseApi
import ru.rikmasters.circular_chart_impl.data.CircularChartRepository
import ru.rikmasters.circular_chart_impl.domain.GetUsersUseCase
import ru.rikmasters.circular_chart_impl.presentation.viewmodel.CircularChartViewModel

val circularChartModule = module {
    single { CircularChartRepository(get()) } bind CircularChartRepositoryApi::class
    factory { GetUsersUseCase(get()) } bind GetUsersUseCaseApi::class
    viewModel { CircularChartViewModel(get()) }
}