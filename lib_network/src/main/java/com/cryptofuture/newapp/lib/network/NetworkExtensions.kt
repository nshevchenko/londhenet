package com.cryptofuture.londhenet.lib.network

import com.cryptofuture.londhenet.lib.network.ErrorState.AuthorizationError
import retrofit2.Response
import timber.log.Timber
import java.net.ConnectException
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T : Any> Response<T>.toResult(): ResultType<T> {
    return when {
        isSuccessful -> body()?.let {
            ResultType.Success(it)
        } ?: ResultType.Error(ErrorState.GenericError(Exception()))
        else -> handleUnsuccessful()
    }
}

fun Response<Unit>.toEmptyResult(): ResultType<Unit> {
    return when {
        isSuccessful -> ResultType.Success(Unit)
        else -> handleUnsuccessful()
    }
}

private fun <T : Any> Response<T>.handleUnsuccessful(): ResultType<T> {
    return when (HTTP_FORBIDDEN) {
        code() -> {
            ResultType.Error(AuthorizationError(errorCode = extractErrorCode()))
        }
        else -> ResultType.Error(ErrorState.NetworkError(errorCode = extractErrorCode()))
    }
}

private fun <T : Any> Response<T>.extractErrorCode(): Int {
    return this.code()
}

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResultType<T> {
    return try {
        call().toResult()
    } catch (exception: Exception) {
        handleError(exception)
    }
}

suspend fun safeApiCallWithEmptyResponse(call: suspend () -> Response<Unit>): ResultType<Unit> {
    return try {
        call().toEmptyResult()
    } catch (exception: Exception) {
        handleError(exception)
    }
}

private fun <T : Any> handleError(exception: java.lang.Exception): ResultType<T> {
    return when (exception) {
        is ConnectException, is SocketTimeoutException, is UnknownHostException -> ResultType.Error(
            ErrorState.ConnectivityError
        )
        else -> {
            Timber.w(exception)
            ResultType.Error(ErrorState.GenericError(exception))
        }
    }
}
