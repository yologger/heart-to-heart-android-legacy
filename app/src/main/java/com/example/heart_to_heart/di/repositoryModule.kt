package com.example.heart_to_heart.di

import com.example.heart_to_heart.data.repository.DefaultAuthorizationRepository
import com.example.heart_to_heart.data.repository.DefaultPostRepository
import com.example.heart_to_heart.data.repository.DefaultUserRepository
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.domain.repository.PostRepository
import com.example.heart_to_heart.domain.repository.UserRepository
import org.koin.dsl.module

var repositoryModule = module {
    single<AuthorizationRepository> { DefaultAuthorizationRepository(get(), get()) }
    single<PostRepository> { DefaultPostRepository(get(), get()) }
    single<UserRepository> { DefaultUserRepository(get(), get()) }
}