package com.example.map.model

import com.google.android.libraries.maps.model.LatLng

data class Pin(
    val position : LatLng,
    val data: Long
)