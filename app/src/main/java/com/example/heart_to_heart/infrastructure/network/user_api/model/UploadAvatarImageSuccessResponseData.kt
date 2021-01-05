package com.example.heart_to_heart.infrastructure.network.user_api.model

import com.google.gson.annotations.SerializedName

data class UploadAvatarImageSuccessResponseData
constructor(
    @SerializedName("uri") val uri: String
)