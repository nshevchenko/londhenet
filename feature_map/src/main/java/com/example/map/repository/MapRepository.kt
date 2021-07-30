package com.example.map.repository

import com.example.map.model.Pin
import com.google.android.libraries.maps.model.LatLng
import javax.inject.Inject
import javax.sql.DataSource

class MapRepository @Inject constructor(
//    private val fileDataSource: DataSource
) {

    suspend fun loadPins(): List<Pin> {
        return listOf(Pin(LatLng(0.0, 0.0), 1))
    }
}
