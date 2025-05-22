package ru.rikmasters.network_client_impl.dto

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import ru.rikmasters.network_client_api.entity.Image

@InternalSerializationApi
@Serializable
data class ImageDto(
    override val id: Int,
    override val url: String,
    override val type: String
) : Image