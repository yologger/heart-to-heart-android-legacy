package com.example.heart_to_heart.infrastructure.network.post_api.model

import com.example.heart_to_heart.presentation.model.Post

data class GetAllPostsResultData
constructor(
    val posts: List<Post>
)