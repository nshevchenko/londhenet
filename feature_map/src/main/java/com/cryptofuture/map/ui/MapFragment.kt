package com.cryptofuture.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.cryptofuture.map.R
import com.cryptofuture.map.databinding.FragmentMapBinding
import com.cryptofuture.map.model.MapPin
import com.cryptofuture.map.model.Pin
import com.cryptofuture.map.viewmodel.MapViewModel
import com.cryptofuture.map.viewmodel.MapViewModel.Event
import com.cryptofuture.londhenet.lib.core.feature.BaseFragment
import com.cryptofuture.londhenet.lib.core.util.observe
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.CameraPosition
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.LatLngBounds
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import javax.inject.Inject

class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    @Inject
    lateinit var viewModel: MapViewModel

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
        binding.searchBar.setOnApplyWindowInsetsListener { v, insets ->
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
            setMinZoomPreference(10F)
            setLatLngBoundsForCameraTarget(
                LatLngBounds(LatLng(51.393014, -0.367876), LatLng(51.610149, 0.063337))
            )
            setOnMarkerClickListener {
                viewModel.onMarkerClicked(it.tag.toString())
                true
            }
        }
    }

    private fun displayPins(pins: List<MapPin>) {
        pins.forEach { pin ->
            val vectorResId =
                if (pin.online) R.drawable.hotspot_online else R.drawable.hotspot_offline
            val marker = MarkerOptions().apply {
                position(pin.position)
                title(pin.name)
                icon(bitmapDescriptorFromVector(requireContext(), vectorResId))
            }
            googleMap?.addMarker(marker)?.tag = pin.name
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    private fun handleViewModelEvents(event: Event) {
        when (event) {
            Event.NavigateToMain -> navController.navigateUp()
            is Event.ShowError -> showError(event.message)
            is Event.ShowDetails -> showPinDetails(event.hotspot)
        }
    }

    private fun showPinDetails(hotspot: Pin?) {
        bottomSheet?.state = BottomSheetBehavior.STATE_COLLAPSED
        binding.name.text = hotspot?.name
        val resource =
            if (hotspot?.online == true) R.drawable.hotspot_online else R.drawable.hotspot_offline
        binding.status.setBackgroundResource(resource)
        binding.perf1.text = getString(R.string.performance1, hotspot?.performance1.toString())
        binding.perf2.text = getString(R.string.performance2, hotspot?.performance2.toString())
    }

    private fun showError(message: Int) {

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
