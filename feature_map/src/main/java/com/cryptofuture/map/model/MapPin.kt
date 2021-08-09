package com.cryptofuture.map.model

import com.google.android.libraries.maps.model.LatLng


data class MapPin(
    val name: String,
    val online: Boolean,
    val position: LatLng
)