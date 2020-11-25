package com.example.heart_to_heart.infrastructure.model

import com.google.gson.annotations.SerializedName

data class GetPostsFailureResponse
constructor(
    @SerializedName("code") val code: Int,
    @SerializedName("error_message") val errorMessage: String
)