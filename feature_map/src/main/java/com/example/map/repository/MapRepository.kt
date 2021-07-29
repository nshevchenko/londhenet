package com.example.map.repository

import com.example.map.model.LoggedInUser
import com.example.newapp.lib.network.ResultType

class MapRepository(
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