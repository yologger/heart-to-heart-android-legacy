package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

enum class ValidateSessionError {
    EXPIRED_ACCESS_TOKEN,
    EXPIRED_REFRESH_TOKEN,
    NETWORK_ERROR
}