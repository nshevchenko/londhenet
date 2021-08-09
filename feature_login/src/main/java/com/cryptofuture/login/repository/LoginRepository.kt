package com.cryptofuture.login.repository

import com.cryptofuture.login.datasource.LoginApi
import com.cryptofuture.login.model.LoggedInUser
import com.cryptofuture.londhenet.lib.core.user.UserSharedPref
import com.cryptofuture.londhenet.lib.network.ResultType
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val dataSource: LoginApi,
    private val loginSharedPreferences: UserSharedPref
) {

    suspend fun login(username: String, password: String): ResultType<LoggedInUser> {
        val result = dataSource.login(username, password)

        if (result is ResultType.Success) {
            setLoggedInUser(result.data)
        }
        return ResultType.Success(LoggedInUser("1234L", "Nik"))
//        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        loginSharedPreferences.saveUser()
    }
}