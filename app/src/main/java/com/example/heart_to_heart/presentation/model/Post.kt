package com.example.heart_to_heart.presentation.model

import com.google.gson.annotations.SerializedName

data class Post
constructor(
    @SerializedName("id") val id: Int,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("userId") val userId: Int,
    @SerializedName("user") val user: User,
    @SerializedName("postImages") val postImages: List<PostImage>
)