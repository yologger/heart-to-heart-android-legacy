package com.example.heart_to_heart.infrastructure.model

import com.google.gson.annotations.SerializedName

data class LogInResponseData
constructor(
    @SerializedName("client_id")
    val clientID: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("access_token")
    var accessToken: String?,
    @SerializedName("refresh_token")
    var refreshToken: String?
)