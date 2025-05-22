package ru.rikmasters.network_client_api.entity

interface UserStats {
    val user_id: Int
    val type: String
    val dates: List<String>
}