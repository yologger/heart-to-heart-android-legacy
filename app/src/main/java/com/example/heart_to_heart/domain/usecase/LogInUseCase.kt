package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.domain.base.BaseUseCase
import io.reactivex.Observable

class LogInUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) : BaseUseCase<Boolean>() {


    override fun call(): Observable<Boolean> {
        TODO("Not yet implemented")
    }
}

