package com.example.map.ui

import com.example.login.R
import com.example.login.databinding.FragmentMapBinding
import com.example.map.viewmodel.MapViewModel
import com.example.map.viewmodel.MapViewModel.Event
import com.example.newapp.lib.core.feature.BaseFragment
import com.example.newapp.lib.core.util.observe
import javax.inject.Inject

class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {

    @Inject
    lateinit var loginViewModel: MapViewModel

    override fun onAfterCreated() {
        loginViewModel.apply {
            observe(event) { handleViewModelEvents(it) }
            binding.viewModel = this
        }
    }

    private fun handleViewModelEvents(event: Event) {
        when (event) {
            Event.NavigateToMain -> navController.navigateUp()
            is Event.ShowError -> showError(event.message)
        }
    }

    private fun showError(message: Int) {

    }
}
