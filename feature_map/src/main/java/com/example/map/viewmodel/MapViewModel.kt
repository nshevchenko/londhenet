package com.example.map.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.R
import com.example.map.repository.MapRepository
import com.example.map.viewmodel.MapViewModel.Event
import com.example.map.viewmodel.MapViewModel.Event.ShowError
import com.example.newapp.lib.core.coroutines.CoroutineDispatchers
import com.example.newapp.lib.network.ResultType
import kotlinx.coroutines.launch

interface MapViewModel {

    sealed class Event {
        object NavigateToMain : Event()
        data class ShowError(val message: Int) : Event()
    }

    val event: LiveData<Event>
}

class MapViewModelImpl(
    private val loginRepository: MapRepository,
    private val dispatchers: CoroutineDispatchers
) : MapViewModel, ViewModel() {

    private val _event = MutableLiveData<Event>()
    override val event: LiveData<Event> = _event

    fun login(username: String, password: String) {
        viewModelScope.launch(dispatchers.io) {
            val result = loginRepository.login(username, password)

            if (result is ResultType.Success) {
                _event.value = Event.NavigateToMain
            } else {
                _event.value = ShowError(message = R.string.login_failed)
            }
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}