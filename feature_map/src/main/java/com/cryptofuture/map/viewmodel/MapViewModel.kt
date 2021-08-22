package com.cryptofuture.map.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptofuture.londhenet.lib.core.coroutines.CoroutineDispatchers
import com.cryptofuture.map.mapper.toUI
import com.cryptofuture.map.model.MapPin
import com.cryptofuture.map.model.Pin
import com.cryptofuture.map.model.PinUI
import com.cryptofuture.map.repository.MapRepository
import com.cryptofuture.map.viewmodel.MapViewModel.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MapViewModel {

    sealed class Event {
        object NavigateToMain : Event()
        data class ShowError(val message: Int) : Event()
        class ShowDetails(val hotspot: PinUI) : Event()
    }

    val event: LiveData<Event>
    val pins: LiveData<List<MapPin>>

    fun loadPins()
    fun onMarkerClicked(id: String?)
}

class MapViewModelImpl @Inject constructor(
    private val mapRepository: MapRepository,
    private val dispatchers: CoroutineDispatchers
) : ViewModel(), MapViewModel {

    private val _event = MutableLiveData<Event>()
    override val event: LiveData<Event>
        get() = _event

    private val _pins = MutableLiveData<List<MapPin>>()
    override val pins: LiveData<List<MapPin>>
        get() = _pins

    private val hotspots = MutableLiveData<List<Pin>>()

    override fun loadPins() {
        viewModelScope.launch(dispatchers.io) {
            val result = mapRepository.loadPins()
            withContext(dispatchers.main) {
                hotspots.value = result
                _pins.value = result.map { it.toMapPin() }
            }
        }
    }

    override fun onMarkerClicked(id: String?) {
        hotspots.value?.firstOrNull { it.name == id }?.let {
            _event.value = Event.ShowDetails(it.toUI())
        }
    }
}

private fun Pin.toMapPin() = MapPin(
    name = this.name,
    online = this.online,
    position = this.position
)
