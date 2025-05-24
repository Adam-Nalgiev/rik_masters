package ru.rikmasters.stats.presentation.view.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.rikmasters.circular_chart_impl.presentation.view.GenderCircularChart
import ru.rikmasters.line_chart_impl.presentation.view.ViewsChart
import ru.rikmasters.stats.R
import ru.rikmasters.stats.presentation.view.theme.Black
import ru.rikmasters.stats.presentation.view.theme.Gray
import ru.rikmasters.stats.presentation.view.theme.Green
import ru.rikmasters.stats.presentation.view.theme.LightGray
import ru.rikmasters.stats.presentation.view.theme.Red
import ru.rikmasters.stats.presentation.view.theme.Transparent
import ru.rikmasters.stats.presentation.view.theme.Typography
import ru.rikmasters.stats.presentation.view.theme.White
import ru.rikmasters.viewers_list_impl.presentation.view.ViewersList

@Composable
internal fun MainScreen(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()
    val lineChartDateItems = listOf(
        stringResource(R.string.by_days),
        stringResource(R.string.by_weeks),
        stringResource(R.string.by_months)
    )
    val circleChartDateItems = listOf(
        stringResource(R.string.today),
        stringResource(R.string.week),
        stringResource(R.string.month),
        stringResource(R.string.all_time)
    )

    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(PaddingValues(horizontal = 16.dp, vertical = 40.dp))
            .navigationBarsPadding()
            .verticalScroll(scrollState)
    ) {
        Text(text = stringResource(R.string.statistic), style = Typography.headlineLarge)

        Text(
            text = stringResource(R.string.visitors),
            style = Typography.headlineMedium,
            modifier = Modifier.padding(top = 40.dp, bottom = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(White, RoundedCornerShape(16.dp))
        ) { PositiveStatCard() }

        //Неизвестно какое состояние оно меняет и как это выглядит, поэтому заглушка
        TabsRow(
            itemsList = lineChartDateItems,
            modifier = Modifier.padding(top = 32.dp)
        )

        ViewsChart(
            modifier = Modifier
                .height(200.dp)
                .padding(top = 8.dp)
        )

        //Возожно было лучше сделать соответствующие текста частью модуля
        Text(
            text = stringResource(R.string.most_often_visitors),
            style = Typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp)
        )

        ViewersList(modifier = Modifier.padding(top = 8.dp))

        Text(
            text = stringResource(R.string.gender_age),
            style = Typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp)
        )

        TabsRow(
            itemsList = circleChartDateItems,
            modifier = Modifier.padding(top = 8.dp)
        )

        GenderCircularChart(modifier = Modifier.padding(top = 8.dp))

        Text(
            text = stringResource(R.string.observers),
            style = Typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(White, RoundedCornerShape(16.dp))
        ) {
            PositiveStatCard()
            NegativeStatCard()
        }
    }

}

//Я не разобрался это хардкод в макете или нет. Входящие данные неопределенны, а ТЗ нет
@Composable
private fun PositiveStatCard(
    modifier: Modifier = Modifier,
    value: Int = 1356, // какое-то значение роста
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
            .heightIn(max = 80.dp)
    ) {
        PositiveChartImage()

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = value.toString(), style = Typography.headlineMedium)
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_up),
                    contentDescription = null,
                    tint = Green,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = stringResource(R.string.visitors_more),
                style = Typography.bodyMedium,
                color = Gray,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

//Правильнее сделать карточку общей для обоих сценариев измениния роста или использовать перегрузку, но воздержусь
@Composable
private fun NegativeStatCard(
    modifier: Modifier = Modifier,
    value: Int = 10, // какое-то значение падения
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
            .heightIn(max = 80.dp)
    ) {
        NegativeChartImage()

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = value.toString(), style = Typography.headlineMedium)
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = Red,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = stringResource(R.string.visitors_less),
                style = Typography.bodyMedium,
                color = Gray,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Composable
private fun PositiveChartImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.img_positive_chart),
        contentDescription = stringResource(R.string.descr_positive_chart),
        modifier = modifier
            .heightIn(min = 200.dp)
            .padding(8.dp)
    )
}

@Composable
private fun NegativeChartImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.img_negative_chart),
        contentDescription = stringResource(R.string.descr_positive_chart),
        modifier = modifier
            .heightIn(min = 200.dp)
            .padding(8.dp)
    )
}

//Кастомная реализация табов. Реализация из материал не достаточно кастомизируема
@Composable
private fun TabsRow(
    modifier: Modifier = Modifier,
    itemsList: List<String>
) {
    val scrollState = rememberScrollState()

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .widthIn(min = LocalWindowInfo.current.containerSize.width.dp)
            .horizontalScroll(scrollState)
    ) {
        var selectedTab by remember { mutableStateOf(itemsList.first()) }

        itemsList.forEachIndexed { index, label ->
            val isSelected = label == selectedTab
            val backgroundColor = if (isSelected) Red else Transparent
            val textColor = if (isSelected) White else Black
            val borderStroke =
                if (isSelected) BorderStroke(0.dp, Transparent) else BorderStroke(1.dp, LightGray)

            OutlinedButton(
                onClick = { selectedTab = itemsList[index] },
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor),
                shape = RoundedCornerShape(32.dp),
                border = borderStroke,
                contentPadding = PaddingValues(vertical = 0.dp, horizontal = 32.dp),
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            ) {
                Text(text = label, style = Typography.bodyMedium, color = textColor)
            }
        }
    }
}