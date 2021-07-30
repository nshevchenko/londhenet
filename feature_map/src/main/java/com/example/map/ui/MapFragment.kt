package com.example.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.map.R
import com.example.map.databinding.FragmentMapBinding
import com.example.map.model.Pin
import com.example.map.viewmodel.MapViewModel
import com.example.map.viewmodel.MapViewModel.Event
import com.example.newapp.lib.core.feature.BaseFragment
import com.example.newapp.lib.core.util.observe
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.MarkerOptions
import javax.inject.Inject

class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    @Inject
    lateinit var viewModel: MapViewModel

    private var googleMap: GoogleMap? = null

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
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    private fun onMapReady(map: GoogleMap) {
        googleMap = map
        customiseMap()
        viewModel.loadPins()
    }

    private fun customiseMap() {
    }

    private fun displayPins(it: List<Pin>) {
        addMarkers(it)
    }

    private fun addMarkers(pins: List<Pin>) {
        pins.forEach { pin ->
            val marker = MarkerOptions().apply {
                position(pin.position)
            }
            googleMap?.addMarker(marker)
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
        }
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
