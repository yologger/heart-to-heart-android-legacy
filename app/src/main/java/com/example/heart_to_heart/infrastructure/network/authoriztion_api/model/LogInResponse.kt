package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

import com.google.gson.annotations.SerializedName

data class LogInResponse
constructor(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: LogInResponseData
)