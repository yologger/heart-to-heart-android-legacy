package com.example.heart_to_heart.infrastructure.network.authoriztion.service

import com.example.heart_to_heart.infrastructure.model.GetPostsResponse
import retrofit2.http.GET
import retrofit2.Call

interface PostsService {
    @GET("/post/test")
    fun getPosts(): Call<GetPostsResponse>
}