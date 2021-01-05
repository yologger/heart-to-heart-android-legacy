package com.example.heart_to_heart.data.model

data class Session
constructor(
    val email: String,
    val profile: Profile,
    val tokens: Tokens
)