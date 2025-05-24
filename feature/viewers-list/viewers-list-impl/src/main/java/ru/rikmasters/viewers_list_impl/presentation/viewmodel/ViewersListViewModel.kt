package ru.rikmasters.viewers_list_impl.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.rikmasters.network_client_api.entity.User
import ru.rikmasters.network_client_api.entity.UserStats
import ru.rikmasters.viewers_list_api.domain.GetStatisticUseCaseApi
import ru.rikmasters.viewers_list_api.domain.GetUsersListUseCaseApi
import ru.rikmasters.viewers_list_api.presentation.ViewerListViewModelApi
import ru.rikmasters.viewers_list_impl.presentation.model.UserUiModel

class ViewersListViewModel(
    override val getStatisticUseCaseApi: GetStatisticUseCaseApi,
    override val getUsersListUseCaseApi: GetUsersListUseCaseApi
) : ViewModel(), ViewerListViewModelApi {

    private var _usersFlow = MutableStateFlow<List<UserUiModel>>(emptyList())
    val usersFlow = _usersFlow.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val stat = requireStatistic()
            val users = requireUsers()

            val usersWithViewsStatistic = calculateUserStats(stat, users)

            _usersFlow.value = usersWithViewsStatistic
        }
    }

    private suspend fun requireStatistic(): List<UserStats> {
        return viewModelScope.async(Dispatchers.IO) {
            getStatisticUseCaseApi.execute().statistics
        }.await()
    }

    private suspend fun requireUsers(): List<User> {
        return viewModelScope.async(Dispatchers.IO) {
            getUsersListUseCaseApi.execute().users
        }.await()
    }

    private fun calculateUserStats(
        statistic: List<UserStats>,
        users: List<User>
    ): List<UserUiModel> {
        val usersViewCounts = calculateViewStat(statistic)
        return getUsersWithStatistic(usersViewCounts, users)
    }

    private fun calculateViewStat(statistic: List<UserStats>): List<Pair<Int, Int>> { // ID, Просмотры
        val listOfUsers = mutableSetOf<Pair<Int, Int>>()

        for (i in statistic) {
            val id = i.user_id
            var viewCount = 0
            val userViews = statistic.filter { it.user_id == id }
            for (j in userViews) {
                if (j.type == "view") {
                    viewCount += j.dates.size
                }
            }
            listOfUsers.add(Pair(id, viewCount))
        }

        return listOfUsers.toList()
    }

    private fun getUsersWithStatistic(
        statistic: List<Pair<Int, Int>>,
        users: List<User>
    ): List<UserUiModel> {
        val usersList = mutableListOf<UserUiModel>()

        statistic.forEach { statistic ->
            users.forEach { user ->
                if (statistic.first == user.id) {
                    usersList.add(
                        createUiModel(
                            id = user.id,
                            username = user.username,
                            age = user.age,
                            isOnline = user.isOnline,
                            avatar = user.files.first().url,
                            viewsCount = statistic.second
                        )
                    )
                }
            }
        }

        Log.d("LIST", usersList.toList().sortedBy { it.viewsCount }.reversed().toString())
        return usersList.toList().sortedBy { it.viewsCount }.reversed()
    }

    private fun createUiModel(
        id: Int,
        username: String,
        age: Int,
        isOnline: Boolean,
        avatar: String,
        viewsCount: Int
    ) = UserUiModel(id, username, age, isOnline, avatar, viewsCount)

}