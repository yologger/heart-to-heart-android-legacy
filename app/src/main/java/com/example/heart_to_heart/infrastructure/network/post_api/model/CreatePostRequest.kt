package com.example.heart_to_heart.infrastructure.network.post_api.model

data class CreatePostRequest
constructor(
    val content: String,
    val uris: List<String>
)