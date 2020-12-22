package com.example.heart_to_heart.data.repository.dataSource.remote

import com.example.heart_to_heart.data.repository.dataSource.remote.base.RemoteDataSource
import com.example.heart_to_heart.infrastructure.network.post_api.service.PostsService

interface PostsAPI: RemoteDataSource {
    fun getPostsService(): PostsService
}

