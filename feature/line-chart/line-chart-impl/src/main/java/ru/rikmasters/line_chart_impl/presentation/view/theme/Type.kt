package ru.rikmasters.line_chart_impl.presentation.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.rikmasters.line_chart_impl.R

internal val Typography = Typography(
    bodyMedium = TextStyle(
        fontSize = 15.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_medium)),
        lineHeight = 16.sp,
        letterSpacing = (-0.1).sp
    )
)
internal val Footnote13Type = TextStyle(
    fontSize = 13.sp,
    fontFamily = FontFamily(Font(R.font.gilroy)),
    color = HalfBlack,
    lineHeight = 16.sp,
    letterSpacing = (-0.04).sp
)
internal val Footnote11Type = TextStyle(
    fontSize = 11.sp,
    fontFamily = FontFamily(Font(R.font.gilroy)),
    color = Gray,
    lineHeight = 11.sp,
    letterSpacing = (-0.11).sp
)