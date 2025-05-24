package ru.rikmasters.stats.presentation.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.rikmasters.stats.R
import ru.rikmasters.stats.presentation.view.theme.Gray
import ru.rikmasters.stats.presentation.view.theme.Green
import ru.rikmasters.stats.presentation.view.theme.Typography

@Composable
internal fun MainScreen(modifier: Modifier = Modifier) {


}

//Я не разобрался это хардкод в макете или нет. Входящие данные неопределенны, а ТЗ нет
@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    value: Int = 1356 // какое-то значение роста
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(8.dp)
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
                color = Gray
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

