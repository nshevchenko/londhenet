package com.example.map.repository

import com.example.map.model.LoggedInUser
import com.example.newapp.lib.network.ResultType
import javax.inject.Inject

class MapRepository @Inject constructor(
//    private val fileDataSource: DataSource
) {

    suspend fun login(username: String, password: String): ResultType<LoggedInUser> {
//        val result = dataSource.login(username, password)
//
//        if (result is ResultType.Success) {
//            setLoggedInUser(result.data)
//        }
        return ResultType.Success(LoggedInUser("1235", "Nik"))
    }
}
