package ru.rikmasters.network_client_impl.dto

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import ru.rikmasters.network_client_api.entity.UserStats

@InternalSerializationApi
@Serializable
data class UserStatsDto(
    override val user_id: Int,
    override val type: String,
    override val dates: List<String>
) : UserStats
