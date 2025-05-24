package ru.rikmasters.viewers_list_api.presentation

import ru.rikmasters.viewers_list_api.domain.GetStatisticUseCaseApi
import ru.rikmasters.viewers_list_api.domain.GetUsersListUseCaseApi

interface ViewerListViewModelApi {
    val getStatisticUseCaseApi: GetStatisticUseCaseApi
    val getUsersListUseCaseApi: GetUsersListUseCaseApi
}