package com.cryptofuture.map.model

import com.google.android.libraries.maps.model.LatLng

data class Pin(
    val name: String,
    val address: String,
    val online: Boolean,
    val performance1: Double,
    val performance2: Double,
    val position: LatLng,
    val directionsPerf: Directions
)

data class Directions(
    val n: Int,
    val ne: Int,
    val e: Int,
    val se: Int,
    val s: Int,
    val sw: Int,
    val w: Int,
    val nw: Int
)