package com.example.heart_to_heart.di

import com.example.heart_to_heart.data.repository.DefaultAuthorizationRepository
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var repositoryModule = module {

    single<AuthorizationRepository> { DefaultAuthorizationRepository(
        get(),
        get(),
        get())
    }
    // single<AuthorizationRepository> { DefaultAuthorizationRepository(get(), get(), get(), get(), get()) }
}