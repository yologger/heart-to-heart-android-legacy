package com.example.heart_to_heart.infrastructure.network.user_api.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part

interface UserService {

    @GET("/user/user/{userId}")
    fun getUserInfo(
        @Part("userID") userID: Int
    )

    @Multipart
    @POST("/user/avatar")
    fun uploadAvatarImage(
        @Part("user_id") userId: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<ResponseBody>
}