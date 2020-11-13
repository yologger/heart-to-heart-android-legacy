package com.example.heart_to_heart.infrastructure.model

import com.google.gson.annotations.SerializedName

data class SignUpResponseData
constructor(
    @SerializedName("email")
    val email: String?,
    @SerializedName("nickname")
    val nickname: String?
)