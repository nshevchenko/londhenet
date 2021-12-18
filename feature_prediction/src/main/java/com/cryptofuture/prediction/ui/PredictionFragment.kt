package com.cryptofuture.prediction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cryptofuture.londhenet.lib.core.feature.BaseFragment
import com.cryptofuture.prediction.R
import com.cryptofuture.prediction.databinding.FragmentPredictionBinding
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.CameraPosition
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.Marker
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PredictionFragment : BaseFragment<FragmentPredictionBinding>(R.layout.fragment_prediction) {

//    @Inject
//    lateinit var viewModel: MapViewModel

    private var googleMap: GoogleMap? = null
    private var selectedPin: Marker? = null
    private var bottomSheet: BottomSheetBehavior<LinearLayout>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val mapView: MapView? = view?.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.onResume()
        return view
    }

    override fun onAfterCreated() {
//        viewModel.apply {
//            observe(event) { handleViewModelEvents(it) }
//            observe(pins) { displayPins(it) }
//            binding.viewModel = this
//        }
        binding.mapView.getMapAsync { map -> onMapReady(map) }
        binding.searchBar.requestApplyInsets()
        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheet?.state = BottomSheetBehavior.STATE_HIDDEN
        adjustInsets()
    }

    private fun adjustInsets() {
        binding.searchBar.setOnApplyWindowInsetsListener { _, insets ->
            val padding = binding.searchBar.paddingStart
            binding.searchBar.setPadding(
                padding,
                padding + insets.systemWindowInsetTop,
                padding,
                padding
            )
            insets
        }
    }


    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    private fun onMapReady(map: GoogleMap) {
        map.animateCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition(LatLng(51.510822, -0.105035), 12F, 0F, 0F)
            )
        )
        googleMap = map
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }
}
