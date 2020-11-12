package com.example.heart_to_heart.infrastructure.model

data class SignUpRequest
constructor(
    var email: String,
    var firstName: String,
    var lastName: String,
    var nickname: String,
    var password: String
)