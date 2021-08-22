package com.cryptofuture.map.repository

import com.cryptofuture.map.model.Directions
import com.cryptofuture.map.model.Pin
import com.google.android.libraries.maps.model.LatLng
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val fileDataSource: MapJsonDataSource
) {

    fun loadPins(): List<Pin> {
        val result = JSONArray(fileDataSource.getPins())
        val list = mutableListOf<Pin>()
        for (i in 0 until result.length()) {
            val item = result.getJSONObject(i).toDomain()
            list.add(item)
        }
        return list
    }
}

private fun JSONObject.toDomain(): Pin =
    Pin(
        name = this.getString("name"),
        address = this.getString("address"),
//        online = this.getString("status") == "online",
        online = true,
        performance1 = this.getDouble("ref_rss"),
        performance2 = this.getDouble("beac_rate"),
        position = LatLng(this.getDouble("lat"), this.getDouble("lng")),
        directionsPerf = Directions(
            n = this.getInt("w_N"),
            ne = this.getInt("w_NE"),
            e = this.getInt("w_E"),
            se = this.getInt("w_SE"),
            s = this.getInt("w_S"),
            sw = this.getInt("w_SW"),
            w = this.getInt("w_W"),
            nw = this.getInt("w_NW")
        )
    )
