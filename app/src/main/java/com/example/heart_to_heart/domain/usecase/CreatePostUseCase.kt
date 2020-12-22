package com.example.heart_to_heart.domain.usecase

import android.net.Uri
import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.domain.repository.PostRepository
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostResult
import io.reactivex.Observable

class CreatePostUseCase
constructor(
    private val postRepository: PostRepository
) : BaseUseCase<CreatePostResult>() {

    lateinit var content: String
    lateinit var imageUris: MutableList<Uri>

    override fun call(): Observable<CreatePostResult> {
        return postRepository.createPost(content, imageUris)
    }
}