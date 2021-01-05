package com.example.heart_to_heart.infrastructure.network.post_api.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @GET("/post/posts")
    fun getPosts(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<ResponseBody>

    @Multipart
    @POST("/post/post")
    fun createPost(
        @Part images: List<MultipartBody.Part>?,
        @Part("content") content: RequestBody,
        @Part("user_id") userId: RequestBody
    ): Call<ResponseBody>
}