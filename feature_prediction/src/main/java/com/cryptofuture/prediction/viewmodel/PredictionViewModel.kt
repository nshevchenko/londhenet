package com.cryptofuture.prediction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptofuture.newapp.lib.core.coroutines.CoroutineDispatchers
import com.cryptofuture.prediction.model.PredictionDetails
import com.cryptofuture.prediction.repository.PredictionRepository
import com.cryptofuture.prediction.viewmodel.PredictionViewModel.Event
import com.google.android.libraries.maps.model.LatLng
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PredictionViewModel {

    sealed class Event {
        class ShowDetails(val details: PredictionDetails) : Event()
    }

    val prediction: LiveData<Event>
    fun predictReward(clickPosition: LatLng)
}

class PredictionViewModelImpl @Inject constructor(
    private val predictionRepository: PredictionRepository,
    private val dispatchers: CoroutineDispatchers
) : ViewModel(), PredictionViewModel {

    private val _prediction = MutableLiveData<Event>()
    override val prediction: LiveData<Event>
        get() = _prediction

    override fun predictReward(clickPosition: LatLng) {
        viewModelScope.launch(dispatchers.io) {
            val result = predictionRepository.predictReward(clickPosition)
            withContext(dispatchers.main) {
                result?.let { _prediction.value = Event.ShowDetails(result) }
            }
        }
    }
}