package com.example.heart_to_heart.infrastructure.model

sealed class SignUpResponse {
    object SUCCESS: SignUpResponse()
    data class FAILURE(val error: SignUpError): SignUpResponse()
}