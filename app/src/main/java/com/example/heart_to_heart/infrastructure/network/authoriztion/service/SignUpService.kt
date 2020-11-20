package com.example.heart_to_heart.infrastructure.network.authoriztion.service

import com.example.heart_to_heart.infrastructure.model.SignUpRequest
import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("/auth/signup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
}