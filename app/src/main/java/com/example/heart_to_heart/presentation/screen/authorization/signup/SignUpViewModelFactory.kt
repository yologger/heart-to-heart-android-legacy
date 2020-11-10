package com.example.heart_to_heart.presentation.screen.authorization.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.heart_to_heart.domain.usecase.LogInUseCase
import com.example.heart_to_heart.domain.usecase.SignUpUseCase
import com.example.heart_to_heart.presentation.screen.authorization.login.LogInViewModel

class SignUpViewModelFactory
constructor(
    private var signUpUseCase: SignUpUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpUseCase::class.java)) {
            return SignUpViewModel(signUpUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}