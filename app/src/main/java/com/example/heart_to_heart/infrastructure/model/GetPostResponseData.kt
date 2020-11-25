package com.example.heart_to_heart.infrastructure.model

import com.example.heart_to_heart.domain.model.Post

data class GetPostResponseData
constructor(
    var posts: List<Post>
)