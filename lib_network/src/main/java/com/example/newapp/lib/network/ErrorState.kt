package com.example.newapp.lib.network

sealed class ErrorState {
    object ConnectivityError : ErrorState()
    data class AuthorizationError(val errorCode: Int) : ErrorState()
    data class NetworkError(val errorCode: Int) : ErrorState()
    data class GenericError(val exception: Exception) : ErrorState()
}
