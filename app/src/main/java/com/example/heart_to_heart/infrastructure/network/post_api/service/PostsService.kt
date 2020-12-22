package com.example.heart_to_heart.infrastructure.network.post_api.service

import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PostsService {
    @Multipart
    @POST("/post/post")
    fun post(
        @Part images: List<MultipartBody.Part>?,
        @Part("content") content: RequestBody
    ): Call<ResponseBody>
}