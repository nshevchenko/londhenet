package com.cryptofuture.map.mapper

import com.cryptofuture.map.R
import com.cryptofuture.map.model.Pin
import com.cryptofuture.map.model.PinUI
import java.math.RoundingMode

fun Pin.toUI() = PinUI(
    name,
    if (online) R.drawable.hotspot_online else R.drawable.hotspot_offline,
    if (online) R.string.online else R.string.offline,
    address,
    roundToTwoDigits(performance1),
    roundToTwoDigits(performance2),
    position,
    listOf(
        directionsPerf.n,
        directionsPerf.ne,
        directionsPerf.e,
        directionsPerf.se,
        directionsPerf.s,
        directionsPerf.sw,
        directionsPerf.w,
        directionsPerf.nw
    )
)

private fun roundToTwoDigits(value: Double): String = value.toBigDecimal()
    .setScale(2, RoundingMode.HALF_EVEN)
    .toPlainString()
