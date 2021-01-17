package com.example.heart_to_heart.infrastructure.network.authoriztion_api

import com.example.heart_to_heart.application.Constants.Companion.BASE_URL
import com.example.heart_to_heart.data.repository.dataSource.remote.AuthorizationAPI

import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.LogInService
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.LogOutService
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.SignUpService
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.token.TokenService
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.token.ValidateSessionService
import com.example.heart_to_heart.infrastructure.network.interceptor.AuthInterceptor
import com.example.heart_to_heart.infrastructure.network.post_api.service.PostService
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


    override fun getValidateSessionService(): ValidateSessionService {
        val okHttpClient = OkHttpClient.Builder()
            // .addNetworkInterceptor(authInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ValidateSessionService::class.java)
        return service
    }
}