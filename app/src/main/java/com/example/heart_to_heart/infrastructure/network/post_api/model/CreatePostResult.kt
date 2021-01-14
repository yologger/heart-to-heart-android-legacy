package com.example.heart_to_heart.infrastructure.network.post_api.model

sealed class CreatePostResult {
    data class SUCCESS(val data: CreatePostResultData): CreatePostResult()
    data class FAILURE(val error: CreatePostError): CreatePostResult()
}