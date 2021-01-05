package com.example.heart_to_heart.infrastructure.network.user_api

import com.example.heart_to_heart.data.repository.dataSource.remote.UserAPI
import com.example.heart_to_heart.infrastructure.network.interceptor.AuthInterceptor
import com.example.heart_to_heart.infrastructure.network.user_api.service.UserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:8000"

class DefaultUserAPI:
    UserAPI {

    private val authInterceptor: AuthInterceptor

    constructor(authInterceptor: AuthInterceptor) {
        this.authInterceptor = authInterceptor
    }

    override fun getUserService(): UserService {
        val okHttpClient = OkHttpClient.Builder()
            // .addNetworkInterceptor(authInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(UserService::class.java)
        return service
    }
}