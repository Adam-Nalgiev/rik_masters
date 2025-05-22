package ru.rikmasters.network_client_impl.dto

import kotlinx.serialization.Serializable
import ru.rikmasters.network_client_api.entity.User

@Serializable
data class UserDto(
    override val user_id: Int,
    override val type: String,
    override val dates: List<String>
) : User
