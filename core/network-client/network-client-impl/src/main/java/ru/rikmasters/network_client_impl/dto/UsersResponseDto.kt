package ru.rikmasters.network_client_impl.dto

import kotlinx.serialization.Serializable
import ru.rikmasters.network_client_api.entity.UsersResponse

@Serializable
data class UsersResponseDto(val fs: String) : UsersResponse
