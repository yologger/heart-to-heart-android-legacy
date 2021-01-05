package com.example.heart_to_heart.infrastructure.network.post_api.model

sealed class GetAllPostsResult {
    data class SUCCESS(val data: GetAllPostsResultData): GetAllPostsResult()
    data class FAILURE(val error: GetAllPostsError): GetAllPostsResult()
}