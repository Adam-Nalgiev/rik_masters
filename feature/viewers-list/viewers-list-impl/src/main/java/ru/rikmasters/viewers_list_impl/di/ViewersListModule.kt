package ru.rikmasters.viewers_list_impl.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.rikmasters.viewers_list_api.data.ViewersListRepositoryApi
import ru.rikmasters.viewers_list_api.domain.GetViewersListUseCaseApi
import ru.rikmasters.viewers_list_impl.data.ViewersListRepository
import ru.rikmasters.viewers_list_impl.domain.GetViewersListUseCase
import ru.rikmasters.viewers_list_impl.presentation.viewmodel.ViewersListViewModel

val viewersListModule = module {
    single { ViewersListRepository(get()) } bind ViewersListRepositoryApi::class
    factory { GetViewersListUseCase(get()) } bind GetViewersListUseCaseApi::class
    viewModel { ViewersListViewModel(get()) }
}