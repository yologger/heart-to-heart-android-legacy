package com.example.heart_to_heart.data.repository.dataSource.remote

import com.example.heart_to_heart.data.repository.dataSource.remote.base.RemoteDataSource
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.LogInService
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.LogOutService
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.SignUpService
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.token.TokenService
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.service.token.ValidateSessionService

interface AuthorizationAPI: RemoteDataSource {
    fun getValidateSessionService(): ValidateSessionService
    fun getSignUpService(): SignUpService
    fun getLogInService(): LogInService
    fun getLogOutService(): LogOutService
    fun getTokenService(): TokenService
}