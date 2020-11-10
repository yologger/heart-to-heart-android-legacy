package com.example.heart_to_heart.presentation.screen.authorization.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.usecase.LogInUseCase

class LogInViewModel
constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {
    fun test() {
        Log.d("YOLO", "test() from LogInViewModel")
        this.logInUseCase.execute()
    }
}