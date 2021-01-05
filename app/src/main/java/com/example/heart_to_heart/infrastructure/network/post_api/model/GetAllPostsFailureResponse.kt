package com.example.heart_to_heart.infrastructure.network.post_api.model

import com.google.gson.annotations.SerializedName

data class  GetAllPostsFailureResponse
constructor(
    @SerializedName("code") val code: Int,
    @SerializedName("error_message") val errorMessage: String
)