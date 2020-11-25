package com.example.heart_to_heart.data.repository.dataSource.remote

import com.example.heart_to_heart.data.repository.dataSource.remote.base.RemoteDataSource
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.LogInService
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.LogOutService
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.SignUpService
import com.example.heart_to_heart.infrastructure.network.authoriztion.service.token.TokenService

interface AuthorizationAPI: RemoteDataSource {
    fun getSignUpService(): SignUpService
    fun getLogInService(): LogInService
    fun getLogOutService(): LogOutService
    fun getTokenService(): TokenService
}