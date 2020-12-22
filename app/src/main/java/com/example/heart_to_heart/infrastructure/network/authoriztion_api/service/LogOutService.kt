package com.example.heart_to_heart.infrastructure.network.authoriztion_api.service

import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.LogOutResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface LogOutService {
    @POST("/auth/logout")
    fun logOut(@Header("Authorization") accessToken: String): Call<LogOutResponse>
}