package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.ValidateSessionResult
import io.reactivex.Observable

class ValidateSessionUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) : BaseUseCase<ValidateSessionResult>() {
    override fun call(): Observable<ValidateSessionResult> {
        return authorizationRepository.validateSession()
    }
}