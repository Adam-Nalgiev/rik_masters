package ru.rikmasters.network_client_impl.dto

import ru.rikmasters.network_client_api.entity.Image

data class ImageDto(
    override val id: Int,
    override val url: String,
    override val type: String
) : Image
