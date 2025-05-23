package ru.rikmasters.viewers_list_impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.rikmasters.viewers_list_api.domain.GetViewersListUseCaseApi
import ru.rikmasters.viewers_list_api.presentation.ViewerListViewModelApi

class ViewersListViewModel(
    override val getViewersListUseCaseApi: GetViewersListUseCaseApi
) : ViewModel(), ViewerListViewModelApi