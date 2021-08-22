package com.cryptofuture.map.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.cryptofuture.londhenet.lib.core.feature.BaseFragment
import com.cryptofuture.londhenet.lib.core.util.observe
import com.cryptofuture.map.R
import com.cryptofuture.map.databinding.FragmentMapBinding
import com.cryptofuture.map.model.MapPin
import com.cryptofuture.map.model.PinUI
import com.cryptofuture.map.viewmodel.MapViewModel
import com.cryptofuture.map.viewmodel.MapViewModel.Event
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.GoogleMap
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.CameraPosition
import com.google.android.libraries.maps.model.LatLng
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
            setOnMarkerClickListener {
                viewModel.onMarkerClicked(it.tag.toString())
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(it.position, 14f))
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

    private fun showPinDetails(hotspot: PinUI) {
        bottomSheet?.state = BottomSheetBehavior.STATE_COLLAPSED
        binding.apply {
            name.text = hotspot.name
            perf1.text = hotspot.performance1
            perf2.text = hotspot.performance2
            address.text = hotspot.address
            address.setOnClickListener { copyToClipBoard(hotspot.address) }
            copyLabel.setOnClickListener { copyToClipBoard(hotspot.address) }
            customiseChart(chart, hotspot)
        }
    }

    private fun customiseChart(chart: RadarChart, hotspot: PinUI) {
        val lightGray = ContextCompat.getColor(requireContext(), R.color.light_gray)
        val color1 = ContextCompat.getColor(requireContext(), R.color.purple_200)
        val color2 = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        val padding = resources.getDimension(R.dimen.spacing_4)
        val labels = listOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        val datatSet =
            RadarDataSet(hotspot.directions.map { RadarEntry(it.first.toFloat()) }, "")
                .apply {
                    setDrawValues(false)
                    lineWidth = 3f
                    setDrawFilled(true)
                    fillColor = color1
                    setColors(color1)
                    valueFormatter = object : IndexAxisValueFormatter() {
                        override fun getFormattedValue(value: Float, axis: AxisBase): String {
                            return labels[value.toInt()]
                        }
                    }
                }

        chart.apply {
            setExtraOffsets(padding, padding, padding, padding)
            webAlpha = 100
            skipWebLineCount = 3
            setTouchEnabled(false)
            xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            xAxis.textColor = lightGray
            yAxis.setDrawLabels(false)
            legend.isEnabled = false
            description.isEnabled = false
            isRotationEnabled = false
            data = RadarData(datatSet)
            chart.animateXY(400, 400, Easing.EaseInOutQuad)
            invalidate()
        }
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
