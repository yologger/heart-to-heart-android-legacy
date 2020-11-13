package com.example.heart_to_heart.infrastructure.model

import com.google.gson.annotations.SerializedName

data class LogInResponse
constructor(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: LogInResponseData
)