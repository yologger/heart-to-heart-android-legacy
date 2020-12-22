package com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.token.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponseData
constructor(
    @SerializedName("client_id") val clientId: String,
    @SerializedName("email") val email: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String
)