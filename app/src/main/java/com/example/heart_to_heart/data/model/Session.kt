package com.example.heart_to_heart.data.model

data class Session
constructor(
    val email: String,
    var profile: Profile,
    var tokens: Tokens
)