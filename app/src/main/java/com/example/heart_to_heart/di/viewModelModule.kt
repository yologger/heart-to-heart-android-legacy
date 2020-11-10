package com.example.heart_to_heart.di

import com.example.heart_to_heart.presentation.screen.authorization.login.LogInViewModel
import com.example.heart_to_heart.presentation.screen.authorization.signup.SignUpViewModel
import com.example.heart_to_heart.presentation.screen.main.follow.FollowViewModel
import com.example.heart_to_heart.presentation.screen.main.home.HomeViewModel
import com.example.heart_to_heart.presentation.screen.main.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { LogInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { FollowViewModel() }
    viewModel { ProfileViewModel(get()) }
}