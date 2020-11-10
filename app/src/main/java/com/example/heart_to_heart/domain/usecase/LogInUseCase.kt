package com.example.heart_to_heart.domain.usecase

import android.util.Log
import com.example.heart_to_heart.domain.`interface`.AuthorizationRepository

class LogInUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) {
    fun execute() {
        Log.d("YOLO", "test() from LogInUseCase")
        authorizationRepository.test()
    }
}