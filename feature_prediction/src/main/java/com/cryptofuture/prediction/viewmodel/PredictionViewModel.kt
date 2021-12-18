package com.cryptofuture.prediction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptofuture.londhenet.lib.core.coroutines.CoroutineDispatchers
import com.cryptofuture.map.model.MapPin
import com.cryptofuture.map.model.Pin
import com.cryptofuture.map.model.PinUI
import com.cryptofuture.prediction.repository.PredictionRepository
import com.cryptofuture.prediction.viewmodel.PredictionViewModel.Event
import kotlinx.coroutines.launch
import okhttp3.CertificatePinner
import javax.inject.Inject

interface PredictionViewModel {

    sealed class Event {
        object NavigateToMain : Event()
        data class ShowError(val message: Int) : Event()
        class ShowDetails(val hotspot: PinUI) : Event()
    }

    val event: LiveData<Event>

    fun loadPins()
}

class PredictionViewModelImpl @Inject constructor(
    private val predictionRepository: PredictionRepository,
    private val dispatchers: CoroutineDispatchers
) : ViewModel(), PredictionViewModel {

    private val _event = MutableLiveData<Event>()
    override val event: LiveData<Event>
        get() = _event

    override fun loadPins() {
        viewModelScope.launch(dispatchers.io) {
//            val result = predictionRepository.loadPins()
//            withContext(dispatchers.main) {
//                hotspots.value = result
//                _pins.value = result.map { it.toMapPin() }
//            }
        }
    }
}