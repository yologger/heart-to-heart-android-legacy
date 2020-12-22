package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

data class LogInRequest
constructor(
    val email: String,
    val password: String
)