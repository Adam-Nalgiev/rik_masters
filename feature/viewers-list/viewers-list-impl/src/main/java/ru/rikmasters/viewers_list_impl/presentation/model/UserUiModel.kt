package ru.rikmasters.viewers_list_impl.presentation.model

data class UserUiModel(
    val id: Int,
    val username: String,
    val age: Int,
    val isOnline: Boolean,
    val avatarUrl: String,
    val viewsCount: Int
)
