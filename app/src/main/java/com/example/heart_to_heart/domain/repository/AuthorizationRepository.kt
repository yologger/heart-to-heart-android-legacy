package com.example.heart_to_heart.domain.repository

import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import io.reactivex.Observable

interface AuthorizationRepository {
    fun signUp(
        email: String,
        firstName: String,
        lastName: String,
        nickname: String,
        password: String
    ): Observable<SignUpResponse>
}