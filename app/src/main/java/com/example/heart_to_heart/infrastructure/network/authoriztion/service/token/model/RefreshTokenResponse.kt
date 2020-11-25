package com.example.heart_to_heart.infrastructure.network.authoriztion.service.token.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse
constructor(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: RefreshTokenResponseData
)