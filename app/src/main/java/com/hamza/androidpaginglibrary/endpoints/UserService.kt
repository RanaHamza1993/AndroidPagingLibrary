package com.hamza.androidpaginglibrary.endpoints

import com.hamza.androidpaginglibrary.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<UserResponse>
}