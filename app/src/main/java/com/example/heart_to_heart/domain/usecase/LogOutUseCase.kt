package com.example.heart_to_heart.domain.usecase

import android.util.Log
import com.example.heart_to_heart.domain.repository.AuthorizationRepository

class LogOutUseCase
constructor(
    private val authorizationRepository: AuthorizationRepository
) {
    fun execute() {
        Log.d("YOLO", "test() from LogOutUseCase")
        authorizationRepository.logOut().subscribe({
            Log.d("YOLO", "result: ${it} from LogOutUseCase")
        })
    }
}