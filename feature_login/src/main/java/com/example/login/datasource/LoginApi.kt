package com.example.login.datasource

import com.example.login.model.LoggedInUser
import com.example.newapp.lib.network.ResultType
import com.example.newapp.lib.network.safeApiCall
import javax.inject.Inject

class LoginApi @Inject constructor(private val service: LoginService) {

    suspend fun login(
        username: String,
        password: String
    ): ResultType<LoggedInUser> = safeApiCall {
        service.login(username, password)
    }
}
