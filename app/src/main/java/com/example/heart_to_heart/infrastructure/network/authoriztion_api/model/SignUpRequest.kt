package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

data class SignUpRequest
constructor(
    val email: String,
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val password: String
)