package com.example.heart_to_heart.infrastructure.network.post_api.model


import com.example.heart_to_heart.presentation.model.Post
import com.google.gson.annotations.SerializedName

data class GetAllPostsSuccessResponseData
constructor(
    @SerializedName("posts") val posts: List<Post>
)