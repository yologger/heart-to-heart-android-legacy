package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.domain.repository.UserRepository
import io.reactivex.Observable

class GetUserInfoUseCase
constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Boolean>() {
    override fun call(): Observable<Boolean> {
        return userRepository.getUserInfo()
    }
}