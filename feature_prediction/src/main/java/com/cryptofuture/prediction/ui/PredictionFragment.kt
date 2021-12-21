package com.cryptofuture.prediction.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cryptofuture.londhenet.lib.core.feature.BaseFragment
import com.cryptofuture.londhenet.lib.core.util.observe
import com.cryptofuture.prediction.R
import com.cryptofuture.prediction.databinding.FragmentPredictionBinding
import com.cryptofuture.prediction.model.PredictionDetails
import com.cryptofuture.prediction.viewmodel.PredictionViewModel
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.CameraPosition
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.Marker
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import javax.inject.Inject

class PredictionFragment : BaseFragment<FragmentPredictionBinding>(R.layout.fragment_prediction) {


    @Inject
    lateinit var viewModel: PredictionViewModel

    private var marker: Marker? = null
    private var googleMap: GoogleMap? = null
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
        setupViewModel()
        setupBinding()
        setupBottomSheet()
        adjustInsets()
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
        map.setOnMapClickListener { viewModel.predictReward(it) }
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

    private fun handleViewModelEvents(event: PredictionViewModel.Event) {
        when(event){
            is PredictionViewModel.Event.ShowDetails -> {
                drawPin(event.details.position)
                showDetails(event.details)
            }
        }
    }

    private fun showDetails(details: PredictionDetails) {
        bottomSheet?.state = BottomSheetBehavior.STATE_COLLAPSED
        binding.apply {
            perf1.text = "${details.refRss} dBi"
            perf2.text =  "${details.reward}"
        }
    }

    private fun drawPin(position: LatLng) {
        marker?.remove()
        val vectorResId = R.drawable.hotspot_online
        val markerOptions = MarkerOptions().apply {
            position(position)
            icon(bitmapDescriptorFromVector(requireContext(), vectorResId))
        }
        marker = googleMap?.addMarker(markerOptions)
    }

    private fun setupViewModel() {
        viewModel.apply {
            observe(prediction) { handleViewModelEvents(it) }
            binding.viewModel = this
        }
    }

    private fun setupBinding() {
        binding.mapView.getMapAsync { map -> onMapReady(map) }
        binding.searchBar.requestApplyInsets()
    }

    private fun setupBottomSheet() {
        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet)
        bottomSheet?.state = BottomSheetBehavior.STATE_HIDDEN
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
}
