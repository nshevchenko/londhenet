package com.example.login.repository

import com.example.login.datasource.LoginApi
import com.example.login.model.LoggedInUser
import com.example.newapp.lib.network.ResultType
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val dataSource: LoginApi
//    private val loginSharedPreferences: UserSharedPref
) {

    suspend fun login(username: String, password: String): ResultType<LoggedInUser> {
        val result = dataSource.login(username, password)

        if (result is ResultType.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {

    }
}