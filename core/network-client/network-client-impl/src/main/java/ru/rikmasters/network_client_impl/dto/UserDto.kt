package ru.rikmasters.network_client_impl.dto

import ru.rikmasters.network_client_api.entity.User

data class UserDto(
    override val user_id: Int,
    override val type: String,
    override val dates: List<String>
) : User
