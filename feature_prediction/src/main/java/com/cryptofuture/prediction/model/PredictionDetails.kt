package com.cryptofuture.prediction.model

import com.google.android.libraries.maps.model.LatLng

data class PredictionDetails(
    val position: LatLng,
    val refRss: Double,
    val reward: Double
)
