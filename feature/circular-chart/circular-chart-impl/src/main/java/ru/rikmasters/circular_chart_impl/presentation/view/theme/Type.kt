package ru.rikmasters.circular_chart_impl.presentation.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.rikmasters.circular_chart_impl.R

internal val Typography = Typography(
    bodyMedium = TextStyle(
        fontSize = 15.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_medium)),
        color = Black,
        lineHeight = 16.sp,
        letterSpacing = (-0.1).sp
    ),
    bodySmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_medium)),
        color = Black,
        lineHeight = 11.sp,
        letterSpacing = (-0.11).sp
    )
)

internal val Footnote13Type = TextStyle(
    fontSize = 13.sp,
    fontFamily = FontFamily(Font(R.font.gilroy)),
    color = Black,
    lineHeight = 16.sp,
    letterSpacing = (-0.04).sp
)