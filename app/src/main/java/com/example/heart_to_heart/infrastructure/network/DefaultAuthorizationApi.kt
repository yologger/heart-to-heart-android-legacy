package com.example.heart_to_heart.infrastructure.network

import com.example.heart_to_heart.infrastructure.model.LogInRequest
import com.example.heart_to_heart.infrastructure.model.LogInResponse
import com.example.heart_to_heart.infrastructure.model.SignUpRequest
import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.Body
import retrofit2.http.POST

interface DefaultAuthorizationApi {
    @POST("/auth/signup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>

    @POST("/auth/login")
    // fun logIn(@Body request: LogInRequest): Call<ResponseBody>
    fun logIn(@Body request: LogInRequest): Call<LogInResponse>
}





