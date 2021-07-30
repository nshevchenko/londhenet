package com.example.map.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.map.repository.MapRepository
import com.example.map.viewmodel.MapViewModel.Event
import com.example.newapp.lib.core.coroutines.CoroutineDispatchers
import javax.inject.Inject

interface MapViewModel {

    sealed class Event {
        object NavigateToMain : Event()
        data class ShowError(val message: Int) : Event()
    }

    val event: LiveData<Event>
}

class MapViewModelImpl @Inject constructor(
    private val mapRepository: MapRepository,
    private val dispatchers: CoroutineDispatchers
) : ViewModel(), MapViewModel {

    private val _event = MutableLiveData<Event>()
    override val event: LiveData<Event> = _event

}