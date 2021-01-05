package com.example.heart_to_heart.data.repository.dataSource.remote

import com.example.heart_to_heart.data.repository.dataSource.remote.base.RemoteDataSource
import com.example.heart_to_heart.infrastructure.network.post_api.service.PostService

interface PostAPI: RemoteDataSource {
    fun getPostService(): PostService
}

