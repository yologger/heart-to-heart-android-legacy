package com.example.heart_to_heart.presentation.screen.authorization.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.usecase.SignUpUseCase

class SignUpViewModel
constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    fun test() {
        Log.d("YOLO", "test() from SignUpViewModel")
        this.signUpUseCase.execute()
    }
}