package com.example.heart_to_heart.di

import com.example.heart_to_heart.presentation.screen.AppViewModel
import com.example.heart_to_heart.presentation.screen.authorization.login.LogInViewModel
import com.example.heart_to_heart.presentation.screen.authorization.signup.SignUpViewModel
import com.example.heart_to_heart.presentation.screen.main.MainViewModel
import com.example.heart_to_heart.presentation.screen.main.follow.FollowViewModel
import com.example.heart_to_heart.presentation.screen.main.follow.follower.FollowerViewModel
import com.example.heart_to_heart.presentation.screen.main.follow.following.FollowingViewModel
import com.example.heart_to_heart.presentation.screen.main.home.HomeViewModel
import com.example.heart_to_heart.presentation.screen.main.home.create_post.CreatePostViewModel
import com.example.heart_to_heart.presentation.screen.main.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { AppViewModel(get()) }
    viewModel { LogInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { FollowViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { CreatePostViewModel(get()) }
    viewModel { FollowingViewModel() }
    viewModel { FollowerViewModel() }
}