package ru.rikmasters.network_client_impl.dto

import ru.rikmasters.network_client_api.entity.User

data class UserDto(
    override val id: Int,
    override val sex: String,
    override val username: String,
    override val isOnline: Boolean,
    override val age: Int,
    override val files: List<ImageDto>
) : User