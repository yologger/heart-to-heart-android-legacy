package com.example.heart_to_heart.infrastructure.model

sealed class LogInResult {
    object SUCCESS: LogInResult()
    data class FAILURE(val error: LogInError): LogInResult()
}
