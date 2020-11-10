package com.example.heart_to_heart.di

import com.example.heart_to_heart.domain.usecase.LogInUseCase
import com.example.heart_to_heart.domain.usecase.LogOutUseCase
import com.example.heart_to_heart.domain.usecase.SignUpUseCase
import org.koin.dsl.module

var useCaseModule = module {
    factory { SignUpUseCase(get()) }
    factory { LogInUseCase(get()) }
    factory { LogOutUseCase(get()) }
}