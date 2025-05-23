package ru.rikmasters.viewers_list_api.presentation

import ru.rikmasters.viewers_list_api.domain.GetViewersListUseCaseApi

interface ViewerListViewModelApi {
    val getViewersListUseCaseApi: GetViewersListUseCaseApi
}