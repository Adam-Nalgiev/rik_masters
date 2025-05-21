package ru.rikmasters.line_chart_impl.presentation.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.rikmasters.line_chart_impl.presentation.view.theme.Footnote11Type
import ru.rikmasters.line_chart_impl.presentation.view.theme.Footnote13Type
import ru.rikmasters.line_chart_impl.presentation.view.theme.Gray
import ru.rikmasters.line_chart_impl.presentation.view.theme.HalfBlack
import ru.rikmasters.line_chart_impl.presentation.view.theme.LightGray
import ru.rikmasters.line_chart_impl.presentation.view.theme.Red
import ru.rikmasters.line_chart_impl.presentation.view.theme.Typography
import ru.rikmasters.line_chart_impl.presentation.view.theme.White
import ru.rikmasters.line_chart_impl.presentation.viewmodel.LineChartViewModel

/** Обязательно задать размеры при объявлении*/
@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<Pair<String, Int>>,
    viewModel: LineChartViewModel = viewModel()
) {
    val context = LocalContext.current
    val textMeasurer = rememberTextMeasurer()
    var selectedPoint by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier
            .background(White, RoundedCornerShape(16.dp))
    ) {
        Canvas(
            Modifier
                .fillMaxSize()
                .padding(32.dp)
                .pointerInput(data) {
                    detectTapGestures { tap ->
                        // находим ближайший по X индекс
                        val idx = viewModel.nearestPointIndex(tap, data.size, size.toSize())
                        selectedPoint = idx
                    }
                }
        ) {
            val paddingBetweenValuesAndField = 60f
            val coordinatesFieldHeight = size.height - paddingBetweenValuesAndField
            val minFieldMaxValue = 40f
            val xStepSize = size.width / data.size
            val xValues = data.map { viewModel.dateFormatter(it.first) }
                .map { textMeasurer.measure(it, style = Footnote11Type) }
            val yValues = data.map { it.second.toFloat() }
            val baseStartPadding = 36f
            val yStepSize =
                if (yValues.maxOrNull() != null && yValues.maxOrNull()!! < minFieldMaxValue) {
                    coordinatesFieldHeight / minFieldMaxValue
                } else {
                    coordinatesFieldHeight / (yValues.maxOrNull() ?: minFieldMaxValue)
                }

            //значения оси Х
            xValues.forEachIndexed { index, text ->
                drawText(
                    textLayoutResult = text,
                    topLeft = Offset(
                        x = baseStartPadding + (xStepSize * index),
                        y = size.height.toFloat()
                    )
                )
            }

            //сетка оси X
            val dash = PathEffect.dashPathEffect(floatArrayOf(25f, 15f))

            val gridPaint = Paint().apply {
                color = Gray
                style = PaintingStyle.Stroke
                pathEffect = dash
                strokeWidth = 1.dp.toPx()
            }

            for (i in 0..2) {
                when (i) {
                    0 -> drawContext.canvas.drawLine(
                        Offset(0f, 0f),
                        Offset(size.width.toFloat(), 0f),
                        gridPaint
                    )

                    1 -> drawContext.canvas.drawLine(
                        Offset(0f, coordinatesFieldHeight / 2),
                        Offset(size.width.toFloat(), coordinatesFieldHeight / 2),
                        gridPaint
                    )

                    2 -> drawContext.canvas.drawLine(
                        Offset(0f, coordinatesFieldHeight.toFloat()),
                        Offset(size.width.toFloat(), coordinatesFieldHeight.toFloat()),
                        gridPaint
                    )
                }
            }

            // сохраним список координат, чтобы провести по ним линии
            val listOfPoints = mutableListOf<Offset>()
            yValues.forEachIndexed { index, value ->
                val coordinateOffset = Offset(
                    x = (baseStartPadding + xValues[index].size.width / 2) + (xStepSize * index),
                    y = coordinatesFieldHeight - value * yStepSize
                )
                listOfPoints.add(coordinateOffset)
            }

            // делаем точки кликабельными
            selectedPoint?.let { id ->
                val point = listOfPoints[id]
                drawContext.canvas.drawLine(
                    Offset(point.x, 0f),
                    Offset(point.x, coordinatesFieldHeight),
                    gridPaint.apply { color = Red })
            }

            //рисуем линии и кружки
            for (i in listOfPoints.indices) {
                if (i != listOfPoints.lastIndex)
                    drawLine(
                        color = Red,
                        start = listOfPoints[i],
                        end = listOfPoints[i + 1],
                        strokeWidth = 8f
                    )

                drawCircle(color = Red, radius = 16f, center = listOfPoints[i])
                drawCircle(color = White, radius = 8f, center = listOfPoints[i])
            }

            //Деление и в целом порядок отрисовки обусловлен необходимостью в иерархии отрисовки элементов.
            // Любой другой порядок приводит к неправильному отображению
            selectedPoint?.let { id ->
                val rectWidth = 400f
                val rectHeight = 200f
                val paddingBetweenPointAndRect = 32f
                val point = listOfPoints[id]
                val rectTopLeft = Offset(
                    x = (point.x - rectWidth / 2).coerceIn(0f, size.width - rectWidth),
                    y = (point.y - rectHeight - paddingBetweenPointAndRect).coerceIn(
                        minimumValue = 0f,
                        maximumValue = size.height
                    )
                )
                val rect = Rect(rectTopLeft, Size(width = rectWidth, height = rectHeight))
                val viewsText = textMeasurer.measure(
                    text = viewModel.wordFormatter(yValues[id].toInt(), context),
                    style = Typography.bodyMedium.copy(color = Red),
                )
                val dateText = textMeasurer.measure(
                    text = viewModel.dateFormatter(data[id].first, context),
                    style = Footnote13Type
                )
                val middleOfRectWidthCoord = rectTopLeft.x + rect.size.width / 2
                val middleOfRectHeightCoord = rectTopLeft.y + rect.size.height / 2
                val middleOfTextW = viewsText.size.width / 2
                val textPadding = 12f
                drawRoundRect(
                    color = White,
                    topLeft = rect.topLeft,
                    size = rect.size,
                    cornerRadius = CornerRadius(12.dp.toPx())
                )
                drawRoundRect(
                    color = LightGray,
                    topLeft = rect.topLeft,
                    size = rect.size,
                    style = Stroke(2f),
                    cornerRadius = CornerRadius(12.dp.toPx())
                )
                drawText(
                    textLayoutResult = viewsText,
                    color = Red,
                    topLeft = Offset(
                        middleOfRectWidthCoord - middleOfTextW,
                        middleOfRectHeightCoord - viewsText.size.height - textPadding
                    )
                )
                drawText(
                    textLayoutResult = dateText,
                    color = HalfBlack,
                    topLeft = Offset(
                        middleOfRectWidthCoord - middleOfTextW,
                        middleOfRectHeightCoord + textPadding
                    )
                )
            }
        }
    }
}