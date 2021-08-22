package com.cryptofuture.map.mapper

import com.cryptofuture.map.R
import com.cryptofuture.map.model.Pin
import com.cryptofuture.map.model.PinUI
import java.math.RoundingMode
import kotlin.math.max

fun Pin.toUI() = PinUI(
    name,
    if (online) R.drawable.hotspot_online else R.drawable.hotspot_offline,
    if (online) R.string.online else R.string.offline,
    address,
    roundToTwoDigits(performance1),
    roundToTwoDigits(performance2),
    position,
    listOf(
        max(0, directionsPerf.n) to "N",
        max(0, directionsPerf.ne) to "NE",
        max(0, directionsPerf.e) to "E",
        max(0, directionsPerf.se) to "SE",
        max(0, directionsPerf.s) to "S",
        max(0, directionsPerf.sw) to "SW",
        max(0, directionsPerf.w) to "W",
        max(0, directionsPerf.nw) to "NW"
    )
)

private fun roundToTwoDigits(value: Double): String = value.toBigDecimal()
    .setScale(3, RoundingMode.HALF_EVEN)
    .toPlainString()
