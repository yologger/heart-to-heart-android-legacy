package com.example.heart_to_heart.domain.usecase

import android.net.Uri
import com.example.heart_to_heart.domain.base.BaseUseCase
import com.example.heart_to_heart.domain.repository.UserRepository
import com.example.heart_to_heart.infrastructure.network.user_api.model.UploadAvatarImageResult
import io.reactivex.Observable

class UploadAvatarImageUseCase
constructor(
    private val userRepository: UserRepository
) : BaseUseCase<UploadAvatarImageResult>() {

    lateinit var imageUri: Uri

    override fun call(): Observable<UploadAvatarImageResult> {
        return userRepository.uploadAvatarImage(imageUri)
    }
}