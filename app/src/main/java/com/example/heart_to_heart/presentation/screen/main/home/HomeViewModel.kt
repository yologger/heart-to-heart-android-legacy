package com.example.heart_to_heart.presentation.screen.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.GetPostsUseCase
import com.example.heart_to_heart.infrastructure.network.post_api.model.GetAllPostsError
import com.example.heart_to_heart.infrastructure.network.post_api.model.GetAllPostsResult
import com.example.heart_to_heart.presentation.base.BaseViewModel
import com.example.heart_to_heart.presentation.model.Post
import java.util.concurrent.TimeUnit

class HomeViewModel
constructor(
    private val getAllPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

    private var pageNumber = 0
    private val pageSize = 10
    private var hasMoreItems = true

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val posts: MutableList<Post?> = mutableListOf()
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
                when(result) {
                    is GetAllPostsResult.SUCCESS -> {
                        isLoading.value = false
                        hasMoreItems = result.data.posts.size == pageSize
                        posts.addAll(result.data.posts)
                        postsLiveData.value = posts
                        pageNumber += 1

                        Log.d("YOLO", "POSTS COUNT : ${posts.size}")
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
}