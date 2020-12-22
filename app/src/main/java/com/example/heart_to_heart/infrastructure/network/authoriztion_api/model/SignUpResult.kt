package com.example.heart_to_heart.infrastructure.network.authoriztion_api.model

sealed class SignUpResult {
    object SUCCESS: SignUpResult()
    data class FAILURE(val error: SignUpError): SignUpResult()
}