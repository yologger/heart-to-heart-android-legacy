package com.example.heart_to_heart.infrastructure.network.authoriztion

import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.example.heart_to_heart.data.repository.dataSource.remote.AuthorizationAPI
import com.example.heart_to_heart.di.BASE_URL
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.LogInService
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.LogOutService
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.SignUpService
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.token.TokenService
import com.example.heart_to_heart.infrastructure.network.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DefaultAuthorizationAPI
constructor(
    private val authInterceptor: AuthInterceptor
) : AuthorizationAPI {

    override fun getSignUpService(): SignUpService {
        var okHttpClient = OkHttpClient.Builder()
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(SignUpService::class.java)
        return service
    }

    override fun getLogInService(): LogInService {
        var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(LogInService::class.java)
        return service
    }

    override fun getLogOutService(): LogOutService {
        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(LogOutService::class.java)
        return service
    }

    override fun getTokenService(): TokenService {
        var okHttpClient = OkHttpClient.Builder()
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(TokenService::class.java)
        return service
    }
}