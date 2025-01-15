package com.example.reeltime.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class Moviemodel (
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val title: String,
    val descriptionId: Int,
    val filter: String,
    var isFavorite : Boolean = false
)