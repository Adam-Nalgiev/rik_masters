package ru.rikmasters.viewers_list_impl.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.rikmasters.viewers_list_api.data.ViewersListRepositoryApi
import ru.rikmasters.viewers_list_api.domain.GetStatisticUseCaseApi
import ru.rikmasters.viewers_list_api.domain.GetUsersListUseCaseApi
import ru.rikmasters.viewers_list_impl.data.ViewersListRepository
import ru.rikmasters.viewers_list_impl.domain.GetStatisticUseCase
import ru.rikmasters.viewers_list_impl.domain.GetUsersListUseCase
import ru.rikmasters.viewers_list_impl.presentation.viewmodel.ViewersListViewModel

val viewersListModule = module {
    single { ViewersListRepository(get()) } bind ViewersListRepositoryApi::class
    factory { GetUsersListUseCase(get()) } bind GetUsersListUseCaseApi::class
    factory { GetStatisticUseCase(get()) } bind GetStatisticUseCaseApi::class
    viewModel { ViewersListViewModel(get(), get()) }
}