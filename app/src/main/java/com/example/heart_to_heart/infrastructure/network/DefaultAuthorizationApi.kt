package com.example.heart_to_heart.infrastructure.network

import com.example.heart_to_heart.infrastructure.model.LogInRequest
import com.example.heart_to_heart.infrastructure.model.LogInResponse
import com.example.heart_to_heart.infrastructure.model.SignUpRequest
import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.*

interface DefaultAuthorizationApi {
    @POST("/auth/signup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>

    @POST("/auth/login")
    fun logIn(@Body request: LogInRequest): Call<LogInResponse>

//    @Headers(
//        "accept: application/json",
//        "content-type: application/json",
//        "Authorization: Bearer {access_token}"
//    )
//    fun logOut(@Path("access_token") accessToken: String): Call<LogOutResponse>

    @POST("/auth/logout")
    fun logOut(@Header("Authorization") accessToken: String): Call<LogOutResponse>
}

data class LogOutResponse
constructor(
    val code: Int,
    var message: String
)

data class LogOutFailureResponse
constructor(
    val code: Int,
    var message: String
)




