package com.example.heart_to_heart.infrastructure.model

import com.google.gson.annotations.SerializedName

data class GetPostsResponse
constructor(
    @SerializedName("code") var code: String,
    @SerializedName("message") var message: String,
    @SerializedName("data") var data: GetPostResponseData
)