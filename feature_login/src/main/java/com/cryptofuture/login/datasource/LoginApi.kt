package com.cryptofuture.login.datasource

import com.cryptofuture.login.model.LoggedInUser
import com.cryptofuture.londhenet.lib.network.ResultType
import com.cryptofuture.londhenet.lib.network.safeApiCall
import javax.inject.Inject

class LoginApi @Inject constructor(private val service: LoginService) {

    suspend fun login(
        username: String,
        password: String
    ): ResultType<LoggedInUser> = safeApiCall {
        service.login(username, password)
    }
}
