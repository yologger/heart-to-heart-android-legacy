package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.data.repository.DefaultPostRepository
import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostResult
import io.reactivex.Observable

class UploadImagesUseCase
constructor(
    private val postRepository: DefaultPostRepository
) : BaseUseCase<CreatePostResult>() {
    override fun call(): Observable<CreatePostResult> {
        return Observable.create { emitter ->
            emitter.onNext(CreatePostResult.SUCCESS)
        }
    }
}