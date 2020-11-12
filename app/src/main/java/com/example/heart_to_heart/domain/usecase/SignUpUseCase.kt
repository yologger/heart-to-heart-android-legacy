package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import io.reactivex.Observable
import okhttp3.ResponseBody

class SignUpUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) : BaseUseCase<SignUpResponse>() {

    lateinit var email: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var nickname: String
    lateinit var password: String

    override fun call(): Observable<SignUpResponse> {
        return authorizationRepository.signUp(
            this.email,
            this.firstName,
            this.lastName,
            this.nickname,
            this.password
        )
    }
}