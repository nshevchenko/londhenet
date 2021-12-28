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
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PredictionViewModel {

    sealed class Event {
        class ShowDetails(val details: PredictionDetails) : Event()
    }

    val prediction: LiveData<Event>
    val rewards: LiveData<List<WeightedLatLng>>
    val maxRssi: Double
    val minRssi: Double
    val maxReward: Double
    val minReward: Double

    fun predictReward(clickPosition: LatLng)
    fun loadRewards()
}

class PredictionViewModelImpl @Inject constructor(
    private val predictionRepository: PredictionRepository,
    private val dispatchers: CoroutineDispatchers
) : ViewModel(), PredictionViewModel {

    private val _prediction = MutableLiveData<Event>()
    override val prediction: LiveData<Event>
        get() = _prediction

    private val _rewards = MutableLiveData<List<WeightedLatLng>>()
    override val rewards: LiveData<List<WeightedLatLng>>
        get() = _rewards

    private var _maxRssi: Double = 0.0
    override val maxRssi: Double
        get() = _maxRssi

    private var _minRssi: Double = 0.0
    override val minRssi: Double
        get() = _minRssi

    private var _maxReward: Double = 0.0
    override val maxReward: Double
        get() = _maxReward

    private var _minReward: Double = 0.0
    override val minReward: Double
        get() = _minReward

    override fun predictReward(clickPosition: LatLng) {
        viewModelScope.launch(dispatchers.io) {
            val result = predictionRepository.predictReward(clickPosition)
            withContext(dispatchers.main) {
                result?.let { _prediction.value = Event.ShowDetails(result) }
            }
        }
    }

    override fun loadRewards() {
        viewModelScope.launch(dispatchers.io) {
            val result = predictionRepository.loadRewards()
            _maxReward = result.maxBy { it.reward }?.reward ?: 0.0
            _minReward = result.minBy { it.reward }?.reward ?: 0.0
            _maxRssi = result.maxBy { it.refRss }?.refRss ?: 0.0
            _minRssi = result.minBy { it.refRss }?.refRss ?: 0.0
        }
    }
}