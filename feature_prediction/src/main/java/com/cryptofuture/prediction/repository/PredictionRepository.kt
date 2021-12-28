package com.cryptofuture.prediction.repository

import android.location.Location
import com.cryptofuture.prediction.datasource.PredictionJsonDataSource
import com.cryptofuture.prediction.model.PredictionDetails
import com.google.android.libraries.maps.model.LatLng
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class PredictionRepository @Inject constructor(
    private val fileDataSource: PredictionJsonDataSource
) {

    fun predictReward(clickPosition: LatLng): PredictionDetails? {
        val result = JSONArray(fileDataSource.getPredictionsData())
        var closestPoint: PredictionDetails? = null
        for (i in 0 until result.length()) {
            val item = result.getJSONObject(i).toDomain()
            if (isItemCloser(closestPoint?.position, item.position, clickPosition))
                closestPoint = item
        }
        return closestPoint
    }

    private fun isItemCloser(closestPoint: LatLng?, item: LatLng, clickPosition: LatLng): Boolean {
        val newDistance = getDistance(clickPosition, item)
        val bestDistance = getDistance(closestPoint, clickPosition)
        return newDistance < bestDistance || closestPoint == null
    }

    private fun getDistance(
        pointA: LatLng?,
        pointB: LatLng
    ): Float {
        if (pointA == null) {
            return 0f
        }
        val results = FloatArray(1)
        Location.distanceBetween(
            pointA.latitude,
            pointA.longitude,
            pointB.latitude,
            pointB.longitude,
            results
        )
        return results[0]
    }

    fun loadRewards(): List<PredictionDetails> {
        val result = JSONArray(fileDataSource.getPredictionsData())
        val list = mutableListOf<PredictionDetails>()
        for (i in 0 until result.length()) {
            val item = result.getJSONObject(i).toDomain()
            if(item.reward != 0.0) {
                list.add(item)
            }
        }
        return list
    }
}

private fun JSONObject.toDomain(): PredictionDetails =
    PredictionDetails(
        position = LatLng(this.getDouble("t"), this.getDouble("n")),
        refRss = this.getDouble("d"),
        reward = this.getDouble("r")
    )
