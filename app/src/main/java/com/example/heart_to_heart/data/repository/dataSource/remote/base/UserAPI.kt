package com.example.heart_to_heart.data.repository.dataSource.remote.base

import com.example.heart_to_heart.infrastructure.network.user_api.service.UserService

interface UserAPI: RemoteDataSource {
    fun getUserService(): UserService
}