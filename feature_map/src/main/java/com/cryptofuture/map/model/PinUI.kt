package com.cryptofuture.map.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.libraries.maps.model.LatLng

data class PinUI(
    val name: String,
    @DrawableRes val onlineDrawable: Int,
    @StringRes val onlineText: Int,
    val address: String,
    val performance1: String,
    val performance2: String,
    val position: LatLng,
    val directions: List<Pair<Int, String>>
)