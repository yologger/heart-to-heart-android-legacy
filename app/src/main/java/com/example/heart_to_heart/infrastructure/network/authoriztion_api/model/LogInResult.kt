package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

sealed class LogInResult {
    object SUCCESS: LogInResult()
    data class FAILURE(val error: LogInError): LogInResult()
}
