package com.example.heart_to_heart.presentation.screen.main.follow

import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.usecase.GetAllPostsUseCase

class FollowViewModel
constructor(
    private var getAllPostsUseCase: GetAllPostsUseCase
) : ViewModel() {

    fun getAllPost() {
        getAllPostsUseCase.execute()
    }
}