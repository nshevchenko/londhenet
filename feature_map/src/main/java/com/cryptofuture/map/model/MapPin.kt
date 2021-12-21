package com.cryptofuture.map.model

import com.google.android.libraries.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem


data class MapPin(val name: String, val latLng: LatLng) : ClusterItem {
    override fun getPosition(): LatLng = latLng
    override fun getTitle(): String? = null
    override fun getSnippet(): String? = null
}
