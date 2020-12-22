package com.example.heart_to_heart.infrastructure.network.post_api.model

sealed class CreatePostResult {
    object SUCCESS: CreatePostResult()
    data class FAILURE(val error: CreatePostError): CreatePostResult()
}