package com.example.login.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.R
import com.example.login.repository.LoginRepository
import com.example.login.viewmodel.LoginViewModel.Event
import com.example.login.viewmodel.LoginViewModel.Event.ShowError
import com.example.newapp.lib.core.coroutines.CoroutineDispatchers
import com.example.newapp.lib.network.ResultType
import kotlinx.coroutines.launch
import javax.inject.Inject

interface LoginViewModel {

    sealed class Event {
        object NavigateToMain : Event()
        data class ShowError(val message: Int) : Event()
        object ShowUserNameError : Event()
        object ShowPasswordError : Event()
    }

    val event: LiveData<Event>

    fun onLoginClick(username: String, password: String)
}

class LoginViewModelImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatchers: CoroutineDispatchers
) : LoginViewModel, ViewModel() {

    private val _event = MutableLiveData<Event>()
    override val event: LiveData<Event> = _event

    override fun onLoginClick(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _event.value = Event.ShowUserNameError
        }
        if (!isPasswordValid(password)) {
            _event.value = Event.ShowPasswordError
        }
        login(username, password)
    }

    private fun login(username: String, password: String) {
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