package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.repository.PostRepository

class GetAllPostsUseCase
constructor(
    private var postRepository: PostRepository
)  {
    fun execute() {
        postRepository.getAllPosts()
    }
}