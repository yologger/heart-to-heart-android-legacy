package com.example.heart_to_heart.domain.usecase

import android.util.Log
import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import io.reactivex.Observable

class GetSessionUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) : BaseUseCase<Boolean>() {

    override fun call(): Observable<Boolean> {
        return authorizationRepository.getSession()
    }
}