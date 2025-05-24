package ru.rikmasters.viewers_list_impl.domain

import ru.rikmasters.network_client_api.entity.StatisticsResponse
import ru.rikmasters.viewers_list_api.data.ViewersListRepositoryApi
import ru.rikmasters.viewers_list_api.domain.GetStatisticUseCaseApi

class GetStatisticUseCase(
    override val repository: ViewersListRepositoryApi
) : GetStatisticUseCaseApi {
    override suspend fun execute(): StatisticsResponse {
        return repository.getStatistic()
    }
}