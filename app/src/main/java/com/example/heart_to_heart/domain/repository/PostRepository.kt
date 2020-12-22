package com.example.heart_to_heart.domain.repository

import android.net.Uri
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostResult
import io.reactivex.Observable

interface PostRepository {
    fun getAllPosts()
    fun createPost(content: String, uris: MutableList<Uri>): Observable<CreatePostResult>
}