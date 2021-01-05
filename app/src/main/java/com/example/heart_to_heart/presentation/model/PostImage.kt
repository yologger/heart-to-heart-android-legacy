package com.example.heart_to_heart.presentation.model

import com.google.gson.annotations.SerializedName

data class PostImage
constructor(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String
)