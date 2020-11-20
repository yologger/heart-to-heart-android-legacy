package com.example.heart_to_heart.infrastructure.network.authoriztion.service

import com.example.heart_to_heart.infrastructure.model.LogInRequest
import com.example.heart_to_heart.infrastructure.model.LogInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {
    @POST("/auth/login")
    fun logIn(@Body request: LogInRequest): Call<LogInResponse>
}