package com.example.heart_to_heart.presentation.screen.main.follow

import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.usecase.GetPostsUseCase

class FollowViewModel
constructor(
    private var getAllPostsUseCase: GetPostsUseCase
) : ViewModel() {

    fun getAllPost() {
        getAllPostsUseCase.execute()
    }
}