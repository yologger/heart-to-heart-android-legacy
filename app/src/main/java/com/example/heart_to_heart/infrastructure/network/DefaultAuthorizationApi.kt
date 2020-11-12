package com.example.heart_to_heart.infrastructure.network

import com.example.heart_to_heart.infrastructure.model.SignUpRequest
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.Body
import retrofit2.http.POST

interface DefaultAuthorizationApi {
    @POST("/auth/signup")
    fun signUp(@Body request: SignUpRequest): Call<ResponseBody>
}