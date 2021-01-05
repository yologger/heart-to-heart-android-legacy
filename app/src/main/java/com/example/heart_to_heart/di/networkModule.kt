package com.example.heart_to_heart.di

import com.example.heart_to_heart.data.repository.dataSource.remote.AuthorizationAPI
import com.example.heart_to_heart.data.repository.dataSource.remote.PostAPI
import com.example.heart_to_heart.data.repository.dataSource.remote.UserAPI
import com.example.heart_to_heart.infrastructure.network.interceptor.AuthInterceptor
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.DefaultAuthorizationAPI
import com.example.heart_to_heart.infrastructure.network.post_api.DefaultPostsAPI
import com.example.heart_to_heart.infrastructure.network.user_api.DefaultUserAPI
import org.koin.dsl.module

val BASE_URL = "http://10.0.2.2:8000"
val AUTHORIZATION_BASE_URL = "http://127.0.0.1:8000"


var networkModule = module {
    single<AuthorizationAPI> { DefaultAuthorizationAPI(get()) }
    single<PostAPI>{ DefaultPostsAPI(get()) }
    single<UserAPI>{ DefaultUserAPI(get()) }
    factory { AuthInterceptor(get()) }
}