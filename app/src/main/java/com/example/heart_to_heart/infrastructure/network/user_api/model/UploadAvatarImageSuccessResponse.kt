package com.example.heart_to_heart.infrastructure.network.user_api.model

import com.google.gson.annotations.SerializedName

data class UploadAvatarImageSuccessResponse
constructor(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: UploadAvatarImageSuccessResponseData
)