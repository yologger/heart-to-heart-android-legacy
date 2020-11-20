package com.example.heart_to_heart.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.heart_to_heart.domain.usecase.GetSessionUseCase

class AppViewModelFactory
constructor(
    private var getSessionUseCase: GetSessionUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetSessionUseCase::class.java)) {
            return AppViewModel(getSessionUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}