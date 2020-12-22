package com.example.heart_to_heart.infrastructure.network.post_api.model

import com.google.gson.annotations.SerializedName

data class CreatePostFailureResponse
constructor(
    @SerializedName("code") val code: Int,
    @SerializedName("error_message") var errorMessage: String
)
