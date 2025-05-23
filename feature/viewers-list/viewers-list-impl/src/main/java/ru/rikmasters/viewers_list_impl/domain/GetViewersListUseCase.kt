package ru.rikmasters.viewers_list_impl.domain

import ru.rikmasters.network_client_api.entity.UsersResponse
import ru.rikmasters.viewers_list_api.data.ViewersListRepositoryApi
import ru.rikmasters.viewers_list_api.domain.GetViewersListUseCaseApi

class GetViewersListUseCase(
    override val repository: ViewersListRepositoryApi
) : GetViewersListUseCaseApi {
    override suspend fun execute(): UsersResponse {
        return repository.getUsers()
    }
}