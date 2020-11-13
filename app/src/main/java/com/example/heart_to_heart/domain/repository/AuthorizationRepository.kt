package com.example.heart_to_heart.domain.repository

import com.example.heart_to_heart.infrastructure.model.LogInResult
import com.example.heart_to_heart.infrastructure.model.SignUpResult
import io.reactivex.Observable

interface AuthorizationRepository {

    fun signUp(email: String, firstName: String, lastName: String, nickname: String, password: String): Observable<SignUpResult>
    fun logIn(email: String, password: String): Observable<LogInResult>
}