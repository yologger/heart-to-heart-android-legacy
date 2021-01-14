package com.example.heart_to_heart.infrastructure.network.post_api.model

import com.google.gson.annotations.SerializedName
import com.example.heart_to_heart.presentation.model.Post

data class CreatePostResultData
constructor(
    @SerializedName("post") val post: Post
)