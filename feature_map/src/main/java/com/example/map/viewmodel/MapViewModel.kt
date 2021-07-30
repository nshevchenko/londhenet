package com.example.map.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.map.model.Pin
import com.example.map.repository.MapRepository
import com.example.map.viewmodel.MapViewModel.Event
import com.example.newapp.lib.core.coroutines.CoroutineDispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MapViewModel {

    sealed class Event {
        object NavigateToMain : Event()
        data class ShowError(val message: Int) : Event()
    }

    val event: LiveData<Event>
    val pins: LiveData<List<Pin>>

    fun loadPins()
}

class MapViewModelImpl @Inject constructor(
    private val mapRepository: MapRepository,
    private val dispatchers: CoroutineDispatchers
) : ViewModel(), MapViewModel {

    private val _event = MutableLiveData<Event>()
    override val event: LiveData<Event>
        get() = _event

    private val _pins = MutableLiveData<List<Pin>>()
    override val pins: LiveData<List<Pin>>
        get() = _pins


    override fun loadPins() {
        viewModelScope.launch(dispatchers.io) {
            val pins = mapRepository.loadPins()
            withContext(dispatchers.main) {
                _pins.value = pins
            }
        }
    }

}