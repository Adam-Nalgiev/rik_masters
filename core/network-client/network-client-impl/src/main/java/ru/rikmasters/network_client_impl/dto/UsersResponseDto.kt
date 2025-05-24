package ru.rikmasters.network_client_impl.dto

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import ru.rikmasters.network_client_api.entity.UsersResponse

@InternalSerializationApi
@Serializable
data class UsersResponseDto(
    override val users: List<UserDto>
) : UsersResponse