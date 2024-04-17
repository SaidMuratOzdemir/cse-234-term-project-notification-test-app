package com.saidmuratozdemir.notificationtestapp.model

import androidx.annotation.DrawableRes

data class CardModel(
    val id: String,
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int,
    val shouldShowSwitch: Boolean
)