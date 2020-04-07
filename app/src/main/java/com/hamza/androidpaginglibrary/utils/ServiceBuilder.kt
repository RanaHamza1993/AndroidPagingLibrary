package com.hamza.androidpaginglibrary.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    // Base URL
    private const val URL = "https://reqres.in/api/"
    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logger)
    // Create Retrofit Builder
    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
    // Create Retrofit Instance
    private val retrofit = builder.build()
   operator fun <T> invoke(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

}