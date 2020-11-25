package com.example.heart_to_heart.di

import com.example.heart_to_heart.domain.usecase.*
import org.koin.dsl.module

var useCaseModule = module {
    factory { SignUpUseCase(get()) }
    factory { GetSessionUseCase(get()) }
    factory { LogInUseCase(get()) }
    factory { LogOutUseCase(get()) }
    factory { GetAllPostsUseCase(get()) }
}