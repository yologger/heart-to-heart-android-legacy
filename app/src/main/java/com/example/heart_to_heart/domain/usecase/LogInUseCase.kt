package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.LogInResult
import io.reactivex.Observable

class LogInUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) : BaseUseCase<LogInResult>() {

    lateinit var email: String
    lateinit var password: String

    override fun call(): Observable<LogInResult> {
        return authorizationRepository.logIn(email, password)
    }
}

