package com.example.heart_to_heart.domain.usecase

import android.util.Log
import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.domain.repository.PostRepository
import com.example.heart_to_heart.infrastructure.network.post_api.model.GetAllPostsResult
import io.reactivex.Observable

class GetPostsUseCase
constructor(
    private val postRepository: PostRepository
) : BaseUseCase<GetAllPostsResult>() {

    var page: Int = 0
    var size: Int = 0

    override fun call(): Observable<GetAllPostsResult> {
        return postRepository.getPosts(page, size)
    }
}