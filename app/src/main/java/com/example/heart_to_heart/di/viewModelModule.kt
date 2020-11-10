package com.example.heart_to_heart.di

import com.example.heart_to_heart.presentation.screen.authorization.login.LogInViewModel
import com.example.heart_to_heart.presentation.screen.authorization.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { LogInViewModel() }
    viewModel { SignUpViewModel() }
}