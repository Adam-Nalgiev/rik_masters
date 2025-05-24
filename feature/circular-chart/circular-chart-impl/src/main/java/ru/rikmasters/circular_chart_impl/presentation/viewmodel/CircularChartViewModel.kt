package ru.rikmasters.circular_chart_impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.rikmasters.circular_chart_api.domain.GetUsersUseCaseApi
import ru.rikmasters.circular_chart_api.presentation.CircularChartViewModelApi
import ru.rikmasters.network_client_api.entity.User
import ru.rikmasters.network_client_api.entity.UsersResponse

class CircularChartViewModel(
    override val getUsersUseCaseApi: GetUsersUseCaseApi
) : ViewModel(), CircularChartViewModelApi {

    private var _data = MutableStateFlow<Map<String, Pair<Float, Float>>>(emptyMap())
    val data = _data.asStateFlow()

    private var _totalMaleFemalePercentage = MutableStateFlow<Pair<Float, Float>>(
        Pair(// Лучше бы, конечно, сделать дата класс с именами полов
            0.5f,
            0.5f
        )
    ) //первое значениe - мужчины, второе - женщины
    val totalMaleFemalePercentage = _totalMaleFemalePercentage.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsersUseCaseApi.execute()
            _data.value = prepareData(response)
            _totalMaleFemalePercentage.value = calculateGenderPercentage(response)
        }
    }

    private fun prepareData(users: UsersResponse): Map<String, Pair<Float, Float>> {
        val usersList =
            users.users.filter { it.age in 18..Int.MAX_VALUE } //откуда-то в приходящих данных 15 - летний - убираем его т.к. нет такой категории
        val totalCount = usersList.size
        val preparedData = mutableMapOf<String, Pair<Float, Float>>()
        val ageCategoriesList = listOf(
            "18-21",
            "22-25",
            "26-30",
            "31-35",
            "36-40",
            "41-50",
            ">50"
        )

        val usersInAgeCategories = mutableMapOf<String, List<User>>()

        ageCategoriesList.forEach { ageGroup ->
            usersInAgeCategories[ageGroup] = usersList.filter { it.age in ageGroupRange(ageGroup) }
        }

        usersInAgeCategories.forEach { ageCategory, usersInCategory ->
            var males = usersInCategory.filter { it.sex == "M" }.size
            var females = usersInCategory.filter { it.sex == "W" }.size
            preparedData[ageCategory] =
                Pair(
                    first = convertToPercent(males.toFloat(), totalCount.toFloat()), // мужчин
                    second = convertToPercent(females.toFloat(), totalCount.toFloat()) // женщин
                )
        }
        return preparedData.toMap()
    }

    private fun calculateGenderPercentage(data: UsersResponse): Pair<Float, Float> { //первое значениe - мужчины, второе - женщины
        val users = data.users.filter { it.age in 18..Int.MAX_VALUE }
        val totalSize = users.size.toFloat()
        val males = users.filter { it.sex == "M" }.size.toFloat()
        val females = users.filter { it.sex == "W" }.size.toFloat()
        val malesPercent = males / totalSize
        val femalesPercent = females / totalSize

        return Pair(malesPercent, femalesPercent)
    }

    private fun convertToPercent(int: Float, maxValue: Float): Float {
        val percent = (int / maxValue)
        return percent.toFloat().roundToTwoDecimals()
    }

    private fun ageGroupRange(group: String): IntRange {
        return when (group) {
            "18-21" -> 18..21
            "22-25" -> 22..25
            "26-30" -> 26..30
            "31-35" -> 31..35
            "36-40" -> 36..40
            "41-50" -> 41..50
            else -> IntRange(51, Int.MAX_VALUE)
        }
    }

    private fun Float.roundToTwoDecimals(): Float {
        return "%.2f".format(this).toFloat()
    }
}