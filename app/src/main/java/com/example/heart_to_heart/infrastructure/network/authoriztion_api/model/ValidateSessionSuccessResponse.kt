package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

import com.google.gson.annotations.SerializedName

data class ValidateSessionSuccessResponse
constructor(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)