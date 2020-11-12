package com.example.heart_to_heart.domain.model

data class SignUpData
constructor(
    val email: String,
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val password: String
)