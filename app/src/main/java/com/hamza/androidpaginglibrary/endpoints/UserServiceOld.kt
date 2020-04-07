package com.hamza.androidpaginglibrary.endpoints

import com.hamza.androidpaginglibrary.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserServiceOld {
    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<UserResponse>
}