package com.saidmuratozdemir.notificationtestapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.saidmuratozdemir.notificationtestapp.R


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
val poppinsMedium = FontFamily(
    Font(
        resId = R.font.poppins_medium,
        weight = FontWeight.Bold
    )
)

val poppinsBlack = FontFamily(
    Font(
        resId = R.font.poppins_black,
        weight = FontWeight.Bold
    )
)
