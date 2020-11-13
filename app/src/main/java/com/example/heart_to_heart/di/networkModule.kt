package com.example.heart_to_heart.di

import com.example.heart_to_heart.infrastructure.network.DefaultAuthorizationApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "http://10.0.2.2:8000"
val AUTHORIZATION_BASE_URL = "http://127.0.0.1:8000"


var networkModule = module {
    single {
        var okHttpClient = OkHttpClient.Builder()
            // .addNetworkInterceptor(NetworkIntercepter())
            // .addInterceptor(AppIntercepter())
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        retrofit
    }

    single {
        var retrofit: Retrofit = get()
        var apiService = retrofit.create(DefaultAuthorizationApi::class.java)
        apiService
    }
}