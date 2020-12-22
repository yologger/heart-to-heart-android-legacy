package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

data class LogOutResponse
constructor(
    val code: Int,
    var message: String
)