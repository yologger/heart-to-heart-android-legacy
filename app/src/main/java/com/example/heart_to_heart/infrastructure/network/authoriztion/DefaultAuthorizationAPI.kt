package com.example.heart_to_heart.infrastructure.network.authoriztion

import com.example.heart_to_heart.data.repository.dataSource.remote.AuthorizationAPI
import com.example.heart_to_heart.di.BASE_URL
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.LogInService
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.LogOutService
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.SignUpService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DefaultAuthorizationAPI: AuthorizationAPI {

    override fun getSignUpService(): SignUpService {
        var okHttpClient = OkHttpClient.Builder()
            // .addNetworkInterceptor(NetworkIntercepter())
            // .addInterceptor(AppIntercepter())
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
            // .addNetworkInterceptor(NetworkIntercepter())
            // .addInterceptor(AppIntercepter())
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
            // .addNetworkInterceptor(NetworkIntercepter())
            // .addInterceptor(AppIntercepter())
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(LogOutService::class.java)
        return service
    }
}