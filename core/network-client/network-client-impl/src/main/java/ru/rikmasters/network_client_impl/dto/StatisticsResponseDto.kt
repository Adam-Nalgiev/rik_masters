package ru.rikmasters.network_client_impl.dto

import kotlinx.serialization.Serializable
import ru.rikmasters.network_client_api.entity.StatisticsResponse

@Serializable
data class StatisticsResponseDto(
    override val statistics: List<UserStatsDto>
) : StatisticsResponse
