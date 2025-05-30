package ru.rikmasters.circular_chart_impl.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.rikmasters.circular_chart_impl.R
import ru.rikmasters.circular_chart_impl.presentation.view.theme.Footnote13Type
import ru.rikmasters.circular_chart_impl.presentation.view.theme.Light
import ru.rikmasters.circular_chart_impl.presentation.view.theme.Orange
import ru.rikmasters.circular_chart_impl.presentation.view.theme.Red
import ru.rikmasters.circular_chart_impl.presentation.view.theme.Typography
import ru.rikmasters.circular_chart_impl.presentation.view.theme.White
import ru.rikmasters.circular_chart_impl.presentation.viewmodel.CircularChartViewModel

@Composable
fun GenderCircularChart(
    modifier: Modifier = Modifier,
    size: Dp = 200.dp
) {
    CircularChart(
        modifier = modifier,
        size = size
    )
}

@Composable
private fun CircularChart(
    modifier: Modifier = Modifier,
    viewModel: CircularChartViewModel = koinViewModel(),
    size: Dp
) {
    val genderAgeStatistic = viewModel.data.collectAsState()
    val genderPercentage = viewModel.totalMaleFemalePercentage.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(White, RoundedCornerShape(16.dp))
    ) {
        //Круг
        Circle(
            genderPercentage = genderPercentage.value,
            modifier = Modifier
                .padding(top = 32.dp)
                .size(size)
        )

        AnimatedVisibility(
            visible = genderAgeStatistic.value.isEmpty(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            CircularProgressIndicator(
                color = Red
            )
        }

        //легенда
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            LegendItem(
                color = Red,
                label = stringResource(R.string.male),
                percent = genderPercentage.value.first
            )
            LegendItem(
                color = Orange,
                label = stringResource(R.string.female),
                percent = genderPercentage.value.second
            )
        }

        HorizontalDivider(thickness = 1.dp, color = Light, modifier = Modifier.fillMaxWidth())

        StatisticDescription(genderPercentage = genderAgeStatistic.value)
    }
}

@Composable
private fun Circle(
    modifier: Modifier = Modifier,
    genderPercentage: Pair<Float, Float> //первое значени - мужчины, второе - женщины
) {
    val malePercent = genderPercentage.first
    val femalePercent = 1f - malePercent

    Box(
        modifier
    ) {
        Canvas(
            Modifier.fillMaxSize()
        ) {
            val stroke = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
            val maleSectorEndAngle = 360f * malePercent
            val femaleSectorEndAngle = 360f - maleSectorEndAngle

            //зеркалим объект для соответсвия макету. Хотя можно было и отрисовать сразу по макету,
            // но это немного ломает логику геометрии и понятность кода
            scale(
                scaleX = -1f,
                scaleY = 1f,
                pivot = center
            ) {
                //Рисуем сектор кол-ва мужчин
                if (malePercent != 0f)
                    drawArc(
                        color = Red,
                        startAngle = (-90f) + 4f,
                        sweepAngle = maleSectorEndAngle - 8f,
                        useCenter = false,
                        style = stroke,
                        topLeft = Offset(0f, 0f),
                    )

                //Рисуем сектор кол-ва женщин
                if (femalePercent != 0f)
                    drawArc(
                        color = Orange,
                        startAngle = (-90f) + maleSectorEndAngle + 4f,
                        sweepAngle = femaleSectorEndAngle - 8f,
                        useCenter = false,
                        style = stroke,
                        topLeft = Offset(0f, 0f),
                    )
            }
        }
    }
}

@Composable
private fun LegendItem(
    modifier: Modifier = Modifier,
    color: Color,
    label: String,
    percent: Float
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.size(12.dp)) {
            drawCircle(color = color)
        }

        Spacer(Modifier.padding(8.dp))

        Text(
            text = "$label ${(percent * 100).toInt()}%",
            style = Footnote13Type
        )
    }
}

@Composable
private fun StatisticDescription(
    modifier: Modifier = Modifier,
    genderPercentage: Map<String, Pair<Float, Float>>
) {
    Column(
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        for (statsRow in genderPercentage) {
            DescriptionRow(
                ageCategory = statsRow.key,
                genderPercentage = statsRow.value
            )
        }
    }
}

@Composable
private fun DescriptionRow(
    modifier: Modifier = Modifier,
    ageCategory: String,
    genderPercentage: Pair<Float, Float>//первое значени - мужчины, второе - женщины
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Text(
            text = ageCategory,
            style = Typography.bodyMedium,
            modifier = Modifier.widthIn(min = 52.dp)
        )

        Column {
            LinearStatisticLine(color = Red, percent = genderPercentage.first)
            LinearStatisticLine(color = Orange, percent = genderPercentage.second)
        }
    }
}

@Composable
private fun LinearStatisticLine(
    modifier: Modifier = Modifier,
    color: Color,
    percent: Float
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .padding(end = 24.dp)
    ) {
        val lineEndOffset = size.width * percent
        val text = textMeasurer.measure(
            text = "${(percent * 100).toInt()}%",
            style = Typography.bodySmall
        )

        drawLine(
            color = color,
            strokeWidth = 20f,
            start = Offset(0f, 0f),
            end = Offset(lineEndOffset, 0f),
            cap = StrokeCap.Round
        )

        drawText(
            textLayoutResult = text,
            topLeft = Offset(lineEndOffset + 20f, -12f)
        )
    }
}