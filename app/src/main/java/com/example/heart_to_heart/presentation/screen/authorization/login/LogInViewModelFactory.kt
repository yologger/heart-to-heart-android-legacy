package com.example.heart_to_heart.presentation.screen.authorization.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.heart_to_heart.domain.usecase.LogInUseCase
import com.example.heart_to_heart.domain.usecase.SignUpUseCase
import com.example.heart_to_heart.presentation.screen.authorization.signup.SignUpViewModel

class LogInViewModelFactory
constructor(
    private var logInUseCase: LogInUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogInUseCase::class.java)) {
            return LogInViewModel(logInUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}