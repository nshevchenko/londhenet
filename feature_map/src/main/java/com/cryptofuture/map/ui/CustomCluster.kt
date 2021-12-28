package com.cryptofuture.map.ui

import android.content.Context
import com.cryptofuture.map.R
import com.cryptofuture.map.model.MapPin
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.model.BitmapDescriptor
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

class CustomCluster(
    val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<MapPin>
) : DefaultClusterRenderer<MapPin>(context, map, clusterManager) {

    val greenSign : BitmapDescriptor? = bitmapDescriptorFromVector(context, R.drawable.hotspot_online)

    override fun onBeforeClusterItemRendered(item: MapPin, markerOptions: MarkerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions)
        markerOptions.icon(greenSign)
    }
}