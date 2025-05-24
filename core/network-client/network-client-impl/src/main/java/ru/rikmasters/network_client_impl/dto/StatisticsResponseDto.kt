package ru.rikmasters.network_client_impl.dto

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import ru.rikmasters.network_client_api.entity.StatisticsResponse

@InternalSerializationApi
@Serializable
data class StatisticsResponseDto(
    override val statistics: List<UserStatsDto>
) : StatisticsResponse