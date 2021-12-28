package com.cryptofuture.map.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.cryptofuture.londhenet.lib.core.feature.BaseFragment
import com.cryptofuture.londhenet.lib.core.util.observe
import com.cryptofuture.map.R
import com.cryptofuture.map.databinding.FragmentMapBinding
import com.cryptofuture.map.model.MapPin
import com.cryptofuture.map.model.PinUI
import com.cryptofuture.map.viewmodel.MapViewModel
import com.cryptofuture.map.viewmodel.MapViewModel.Event
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.CameraPosition
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.Marker
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import javax.inject.Inject


class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    @Inject
    lateinit var viewModel: MapViewModel

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
        viewModel.apply {
            observe(event) { handleViewModelEvents(it) }
            observe(pins) { displayPins(it) }
            binding.viewModel = this
        }
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
        customiseMap()
        viewModel.loadPins()
    }

    private fun customiseMap() {
        googleMap?.apply {
            uiSettings.isMapToolbarEnabled = false
            setMinZoomPreference(9F)
        }
    }

    private fun displayPins(pins: List<MapPin>) {
        val metrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics)

        googleMap?.let {
            val clusterManager = ClusterManager<MapPin>(requireContext(), it)
            it.setOnCameraIdleListener(clusterManager)
            it.setOnMarkerClickListener(clusterManager)
            customiseClustering(clusterManager, it, metrics, pins)
        }
    }

    private fun customiseClustering(
        clusterManager: ClusterManager<MapPin>,
        it: GoogleMap,
        metrics: DisplayMetrics,
        pins: List<MapPin>
    ) {
        clusterManager.renderer = CustomCluster(requireContext(), it, clusterManager)
        clusterManager.algorithm =
            NonHierarchicalViewBasedAlgorithm<MapPin>(metrics.widthPixels, metrics.heightPixels)
        clusterManager.addItems(pins)  // 4
        clusterManager.cluster()  // 5
        clusterManager.setAnimation(false)

        clusterManager.getMarkerCollection()
            .setOnMarkerClickListener(getMakerClick(clusterManager))
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    private fun getMakerClick(
        clusterManager: ClusterManager<MapPin>
    ): GoogleMap.OnMarkerClickListener {
        return GoogleMap.OnMarkerClickListener { marker ->
            selectedPin?.let {
                if (clusterManager.markerCollection.getMarkers().contains(it))
                    selectedPin?.setIcon(getGreenCircle())
            }
            selectedPin = marker
            marker.setIcon(
                bitmapDescriptorFromVector(
                    requireContext(),
                    R.drawable.hotspot_online_selected,
                    2F
                )
            )

            viewModel.onMarkerClicked(marker.title)
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 14f))
            true
        }
    }

    private fun getGreenCircle() = bitmapDescriptorFromVector(
        requireContext(),
        R.drawable.hotspot_online
    )

    private fun handleViewModelEvents(event: Event) {
        when (event) {
            Event.NavigateToMain -> navController.navigateUp()
            is Event.ShowError -> showError(event.message)
            is Event.ShowDetails -> showPinDetails(event.hotspot)
        }
    }

    private fun showPinDetails(hotspot: PinUI) {
        bottomSheet?.state = BottomSheetBehavior.STATE_COLLAPSED
        binding.apply {
            name.text = hotspot.name
            perf1.text = "${hotspot.performance1} dBi"
            perf2.text = hotspot.performance2
            address.text = hotspot.address
            address.setOnClickListener { copyToClipBoard(hotspot.address) }
            copyLabel.setOnClickListener { copyToClipBoard(hotspot.address) }
            legendData.text = getLegendData(hotspot.directions)
            legendLabels.text = getLegendLabels()
            customiseChart(requireContext(), chart, hotspot)
        }
    }

    private fun getLegendData(directions: List<Int>): String {
        return directions.mapIndexed { _, i -> "$i" }.joinToString("\n")
    }

    private fun getLegendLabels(): String {
        val labels = listOf("N  ", "NE", "E  ", "SE", "S  ", "SW", "W  ", "NW")
        return labels.joinToString("\n")
    }

    private fun showError(message: Int) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    private fun copyToClipBoard(address: String) {
        (requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).apply {
            val clip = ClipData.newPlainText("label", address)
            setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Text copied!", LENGTH_SHORT).show()
        }
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
