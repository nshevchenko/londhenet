package com.cryptofuture.map.repository

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
        online = this.getString("status") == "online",
        performance1 = this.getDouble("performance1"),
        performance2 = this.getDouble("performance2"),
        position = LatLng(this.getDouble("latitude"), this.getDouble("longitude"))
    )
