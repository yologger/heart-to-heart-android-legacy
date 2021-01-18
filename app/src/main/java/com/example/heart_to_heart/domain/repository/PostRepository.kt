package com.example.heart_to_heart.domain.repository

import android.net.Uri
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostResult
import com.example.heart_to_heart.infrastructure.network.post_api.model.GetAllPostsResult
import io.reactivex.Observable

interface PostRepository {
    fun getPosts(page: Int, size: Int): Observable<GetAllPostsResult>
    fun createPost(content: String, uris: MutableList<Uri>): Observable<CreatePostResult>
    fun likePost(): Observable<Boolean>
    fun unlikePost(): Observable<Boolean>
}