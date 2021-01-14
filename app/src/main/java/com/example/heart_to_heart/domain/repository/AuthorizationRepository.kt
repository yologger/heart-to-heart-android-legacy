package com.example.heart_to_heart.domain.repository

import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.LogInResult
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.SignUpResult
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.ValidateSessionResult
import io.reactivex.Observable

interface AuthorizationRepository {
    fun validateSession(): Observable<ValidateSessionResult>
    fun getSessionState(): Observable<Boolean>
    fun signUp(email: String, firstName: String, lastName: String, nickname: String, password: String): Observable<SignUpResult>
    fun logIn(email: String, password: String): Observable<LogInResult>
    fun logOut(): Observable<Boolean>
    fun removeSession()
}