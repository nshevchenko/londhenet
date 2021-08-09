package com.cryptofuture.login.datasource


import com.cryptofuture.login.model.LoggedInUser
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("/login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<LoggedInUser>
}
