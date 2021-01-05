package com.example.heart_to_heart.presentation.model

import com.google.gson.annotations.SerializedName

data class User
constructor(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("email") val email: String,
    @SerializedName("url") val avatarUrl: String?
)