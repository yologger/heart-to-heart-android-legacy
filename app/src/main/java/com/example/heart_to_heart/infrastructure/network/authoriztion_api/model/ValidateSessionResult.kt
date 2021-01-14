package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

sealed class ValidateSessionResult {
    object SUCCESS: ValidateSessionResult()
    data class FAILURE(val error: ValidateSessionError): ValidateSessionResult()
}