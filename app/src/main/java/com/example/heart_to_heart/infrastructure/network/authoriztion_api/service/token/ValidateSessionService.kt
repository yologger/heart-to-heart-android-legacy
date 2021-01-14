package com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.token

import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ValidateSessionService {
    @GET("/auth/token")
    fun validateAccessToken(): Call<ResponseBody>
}