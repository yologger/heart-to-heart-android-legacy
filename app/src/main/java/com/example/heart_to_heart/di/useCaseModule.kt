package com.example.heart_to_heart.di

import com.example.heart_to_heart.domain.usecase.*
import org.koin.dsl.module

var useCaseModule = module {
    factory { SignUpUseCase(get()) }
    factory { LogInUseCase(get()) }
    factory { LogOutUseCase(get()) }
    factory { GetLogInEventUseCase(get()) }
    factory { GetLogOutEventUseCase(get()) }
}