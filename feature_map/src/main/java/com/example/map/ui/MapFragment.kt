package com.example.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.map.R
import com.example.map.databinding.FragmentMapBinding
import com.example.map.viewmodel.MapViewModel
import com.example.map.viewmodel.MapViewModel.Event
import com.example.newapp.lib.core.feature.BaseFragment
import com.example.newapp.lib.core.util.observe
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import javax.inject.Inject

class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    @Inject
    lateinit var loginViewModel: MapViewModel

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
        loginViewModel.apply {
            observe(event) { handleViewModelEvents(it) }
            binding.viewModel = this
        }
        binding.mapView.getMapAsync { map -> onMapReady(map) }

    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    private fun onMapReady(map: GoogleMap) {
        map.addMarker {
            position(LatLng(0.0, 0.0))
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
