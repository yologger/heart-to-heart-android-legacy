package com.example.heart_to_heart.domain.repository

import android.net.Uri
import com.example.heart_to_heart.infrastructure.network.user_api.model.UploadAvatarImageResult
import io.reactivex.Observable

interface UserRepository {
    fun getUserInfo(): Observable<Boolean>
    fun uploadAvatarImage(imageUri: Uri): Observable<UploadAvatarImageResult>
}