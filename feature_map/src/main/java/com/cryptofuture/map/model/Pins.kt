package com.cryptofuture.map.model

import com.google.android.libraries.maps.model.LatLng

data class Pin(
    val name: String,
    val online: Boolean,
    val performance1: Double,
    val performance2: Double,
    val position : LatLng
)