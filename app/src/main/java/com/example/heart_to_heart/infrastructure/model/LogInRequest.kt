package com.example.heart_to_heart.infrastructure.model

data class LogInRequest
constructor(
    val email: String,
    val password: String
)