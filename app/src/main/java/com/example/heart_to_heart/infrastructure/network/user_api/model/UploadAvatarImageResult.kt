package com.example.heart_to_heart.infrastructure.network.user_api.model

sealed class UploadAvatarImageResult {
    data class SUCCESS(val data: UploadAvatarImageResultData): UploadAvatarImageResult()
    data class FAILURE(val error: UploadAvatarImageError): UploadAvatarImageResult()
}