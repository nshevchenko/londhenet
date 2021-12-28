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
    lateinit var predictionViewModel: PredictionViewModel

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
        map.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition(LatLng(51.510822, -0.105035), 12F, 0F, 0F)
            )
        )
        googleMap = map
        map.setOnMapClickListener {
            predictionViewModel.predictReward(it)
            drawPin(it)
            binding.loading.visibility = View.VISIBLE
        }
        predictionViewModel.loadRewards()
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
        when (event) {
            is PredictionViewModel.Event.ShowDetails -> {
                showDetails(event.details)
            }
        }
    }

    private fun showDetails(details: PredictionDetails) {
        bottomSheet?.state = BottomSheetBehavior.STATE_COLLAPSED

        binding.apply {
            loading.visibility = View.GONE
            perf1.text = "${details.refRss} dBi"
            perf2.text = "${details.reward}"

            maxDatumReward.text = "min : ${predictionViewModel.minReward} / max: ${predictionViewModel.maxReward}"
            maxDatumRssi.text = "min : ${predictionViewModel.minRssi} / max: ${predictionViewModel.maxRssi}"

            progressRef2.max = (predictionViewModel.maxReward * 200).toInt()
            progressRssi.max = ((predictionViewModel.maxRssi - predictionViewModel.minRssi) * 200).toInt()

            progressRef2.progress = (details.reward * 200).toInt()
            progressRssi.progress = ((details.refRss - predictionViewModel.minRssi) * 200).toInt()
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
        predictionViewModel.apply {
            observe(prediction) { handleViewModelEvents(it) }
//            observe(rewards) { showSquares(it) }
            binding.viewModel = this
        }
    }

//    private var provider: HeatmapTileProvider? = null
//    private var overlay: TileOverlay? = null
//
//    private fun showSquares(rewards: List<WeightedLatLng>) {
//        val colors = intArrayOf(
//            Color.rgb(255, 0, 0), // red
//            Color.rgb(102, 225, 0)  // green
//        )
//        val startPoints = floatArrayOf(0.2f, 1f)
//        val gradient = Gradient(colors, startPoints)
//        val maxValue = rewards.maxBy { it.intensity }?.intensity ?: 0.0
//
//        provider = HeatmapTileProvider.Builder()
//            .weightedData(rewards.map { it })
//            .maxIntensity(maxValue)
//            .opacity(0.7)
//            .radius(50)
//            .gradient(gradient)
//            .build()
//
//        overlay = googleMap?.addTileOverlay(TileOverlayOptions().tileProvider(provider))
//
//        googleMap?.setOnCameraIdleListener {
//            googleMap?.cameraPosition?.zoom?.let {
//                if (it < 15 && it > 13) {
//                    provider?.setOpacity(0.7)
//                    overlay?.clearTileCache()
//                } else {
//                    provider?.setRadius(50)
//                    provider?.setOpacity(0.0)
//                    overlay?.clearTileCache()
//                }
//            }
//        }
//    }

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
