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

    @FormUrlEncoded
    @POST("/post/{post_id}/like")
    fun likePost(
        @Path("post_id") postId: Int,
        @Field("user_id") userId: Int
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/post/{post_id}/unlike")
    fun unlikePost(
        @Path("post_id") postId: Int,
        @Query("user_id") userId: Int
    ): Call<ResponseBody>
}