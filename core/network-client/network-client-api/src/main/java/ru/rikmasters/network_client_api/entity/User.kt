package ru.rikmasters.network_client_api.entity

interface User {
    val id: Int
    val sex: String
    val username: String
    val isOnline: Boolean
    val age: Int
    val files: List<Image>
}