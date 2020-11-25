package com.example.heart_to_heart.infrastructure.model

import com.google.gson.annotations.SerializedName

data class RefreshTokensResponse
constructor(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: RefreshTokensResponseData
)