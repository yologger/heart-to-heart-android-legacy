package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import io.reactivex.Observable

class GetLogInEventUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) : BaseUseCase<Unit>() {
    override fun call(): Observable<Unit> {
        return authorizationRepository.getLogInEvent()
    }
}