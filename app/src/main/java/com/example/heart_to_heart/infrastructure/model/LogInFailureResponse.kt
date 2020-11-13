package com.example.heart_to_heart.infrastructure.model

import com.google.gson.annotations.SerializedName

data class LogInFailureResponse
constructor(
    @SerializedName("code")
    val code: String,
    @SerializedName("error_message")
    var errorMessage: String
)