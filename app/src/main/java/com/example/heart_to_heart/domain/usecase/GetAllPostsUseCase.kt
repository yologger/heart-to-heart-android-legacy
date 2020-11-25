package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.repository.PostsRepository

class GetAllPostsUseCase
constructor(
    private var postsRepository: PostsRepository
)  {
    fun execute() {
        postsRepository.getAllPosts()
    }
}