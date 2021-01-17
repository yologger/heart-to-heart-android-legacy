package com.example.heart_to_heart.presentation.screen.main

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.CreatePostUseCase
import com.example.heart_to_heart.domain.usecase.GetPostsUseCase
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostError
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostResult
import com.example.heart_to_heart.infrastructure.network.post_api.model.GetAllPostsError
import com.example.heart_to_heart.infrastructure.network.post_api.model.GetAllPostsResult
import com.example.heart_to_heart.presentation.base.BaseViewModel
import com.example.heart_to_heart.presentation.model.Post
import com.example.heart_to_heart.presentation.screen.main.home.create_post.CreatePostActivityRoutingEvent
import com.example.heart_to_heart.presentation.screen.main.home.create_post.CreatePostViewModel

class PostViewModel
constructor(
    private val createPostUseCase: CreatePostUseCase,
    private val getAllPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

    val createPostActivityRoutingEvent: MutableLiveData<CreatePostActivityRoutingEvent?> =
        MutableLiveData(null)

    val imageUris: MutableList<Uri> = mutableListOf()
    val imageUrisLiveData: MutableLiveData<MutableList<Uri>> = MutableLiveData(imageUris)
    val contentLiveData: MutableLiveData<String> = MutableLiveData("")

    private var pageNumber = 0
    private val pageSize = 10
    private var hasMoreItems = true

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    var posts: MutableList<Post?> = mutableListOf()
    val postsLiveData: MutableLiveData<MutableList<Post?>> = MutableLiveData(posts)


    fun getPosts() {
        if (!hasMoreItems) {
            return
        }
        isLoading.value = true
        getAllPostsUseCase.page = pageNumber
        getAllPostsUseCase.size = pageSize
        getAllPostsUseCase.execute()
            .take(1)
            .subscribe { result ->
                when (result) {
                    is GetAllPostsResult.SUCCESS -> {
                        isLoading.value = false
                        hasMoreItems = result.data.posts.size == pageSize
                        posts.addAll(result.data.posts)
                        postsLiveData.value = posts
                        pageNumber += 1
                        Log.d("YOLO", "POSTs Count: ${posts.size}")
                    }
                    is GetAllPostsResult.FAILURE -> {
                        isLoading.value = false
                        when (result.error) {

                            GetAllPostsError.UNKNOWN_ERROR -> {

                            }
                            GetAllPostsError.NETWORK_CONNECTION_ERROR -> {

                            }
                        }
                    }
                }
            }.apply { disposables.add(this) }
    }

    fun setImages(uris: List<Uri>) {
        imageUris.addAll(uris)
        imageUrisLiveData.value = imageUris
    }

    fun clearSelectedImages() {
        imageUris.clear()
        imageUrisLiveData.value = imageUris
        contentLiveData.value = ""
    }

    fun createPost() {
        createPostUseCase.content = contentLiveData.value!!
        createPostUseCase.imageUris = imageUrisLiveData.value!!
        createPostUseCase.execute().subscribe { result ->
            when (result) {
                is CreatePostResult.SUCCESS -> {
                    val newPost = result.data.post
                    posts.add(0, newPost)
                    postsLiveData.value = posts
                    createPostActivityRoutingEvent.value = CreatePostActivityRoutingEvent.CLOSE
                }
                is CreatePostResult.FAILURE -> {
                    when (result.error) {
                        CreatePostError.UNKNOWN_ERROR -> {
                            createPostActivityRoutingEvent.value =
                                CreatePostActivityRoutingEvent.UNKNOWN_ERROR
                        }
                        else -> {
                            createPostActivityRoutingEvent.value =
                                CreatePostActivityRoutingEvent.UNKNOWN_ERROR
                        }
                    }
                }
            }
        }.apply { disposables.add(this) }
    }


    fun clearFetchedPosts() {
        posts = mutableListOf()
        pageNumber = 0
        hasMoreItems = true
    }

    fun refresh() {
        posts = mutableListOf()
        pageNumber = 0
        hasMoreItems = true
        getPosts()
    }
}