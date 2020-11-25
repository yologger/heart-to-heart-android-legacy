package com.example.heart_to_heart.infrastructure.model

import com.google.gson.annotations.SerializedName

data class RefreshTokensResponseData
constructor(
    @SerializedName("user_id") val userId: String,
    @SerializedName("email") val email: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String
)