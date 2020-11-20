package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.infrastructure.model.SignUpResult
import io.reactivex.Observable

class SignUpUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) : BaseUseCase<SignUpResult>() {

    lateinit var email: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var nickname: String
    lateinit var password: String

    override fun call(): Observable<SignUpResult> {
        return authorizationRepository.signUp(email, firstName, lastName, nickname, password)
    }
}