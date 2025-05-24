package ru.rikmasters.stats.presentation.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.rikmasters.stats.R

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.gilroy_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.14).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gilroy_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.11).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gilroy)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    )
)