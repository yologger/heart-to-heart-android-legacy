package com.example.heart_to_heart.presentation.screen.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.heart_to_heart.domain.usecase.LogOutUseCase

//class ProfileViewModelFactory
//constructor(
//    private var logOutUseCase: LogOutUseCase
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LogOutUseCase::class.java)) {
//            return ProfileViewModel(logOutUseCase) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}