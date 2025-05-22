package ru.rikmasters.circular_chart_impl.domain

import ru.rikmasters.circular_chart_api.data.CircularChartRepositoryApi
import ru.rikmasters.circular_chart_api.domain.GetUsersUseCaseApi
import ru.rikmasters.network_client_api.entity.UsersResponse

class GetUsersUseCase(
    override val repository: CircularChartRepositoryApi
) : GetUsersUseCaseApi {

    override suspend fun execute(): UsersResponse {
        return repository.getUsers()
    }
}