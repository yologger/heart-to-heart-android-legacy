package com.example.heart_to_heart.infrastructure.network.post_api.model

import com.google.gson.annotations.SerializedName

data class CreatePostSuccessResponseData
constructor(
    @SerializedName("images") val images: List<String>
)