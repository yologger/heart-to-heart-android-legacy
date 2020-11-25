package com.example.heart_to_heart.infrastructure.network.post

import com.example.heart_to_heart.data.repository.dataSource.remote.PostsAPI
import com.example.heart_to_heart.infrastructure.network.interceptor.AuthInterceptor
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.PostsService
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:8000"

class DefaultPostsAPI
constructor(
    private val authInterceptor: AuthInterceptor
) : PostsAPI {

    override fun getPostsService(): PostsService {
        var okHttpClient = OkHttpClient.Builder()
            // .addNetworkInterceptor(authInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(PostsService::class.java)
        return service
    }
}
