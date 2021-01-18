package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.domain.repository.PostRepository
import io.reactivex.Observable

class LikePostUseCase
constructor(
    private val postRepository: PostRepository
) : BaseUseCase<Boolean>() {

    override fun call(): Observable<Boolean> {
        return postRepository.likePost()
    }
}