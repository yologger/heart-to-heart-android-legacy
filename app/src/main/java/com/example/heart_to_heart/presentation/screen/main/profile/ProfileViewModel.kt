package com.example.heart_to_heart.presentation.screen.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.usecase.LogOutUseCase

class ProfileViewModel
constructor(
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    fun logOut() {
        logOutUseCase.execute()
    }

    fun test() {
        Log.d("YOLO", "test() from ProfileViewModel")
        logOutUseCase.execute()
    }
}