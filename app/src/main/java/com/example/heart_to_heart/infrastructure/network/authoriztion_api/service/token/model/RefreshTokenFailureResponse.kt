package com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.token.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenFailureResponse
constructor(
    @SerializedName("code") val code: String,
    @SerializedName("error_message") val errorMessage: String
)