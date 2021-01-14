package com.example.heart_to_heart.data.repository

import android.net.Uri
import android.util.Log
import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.example.heart_to_heart.data.repository.dataSource.remote.PostAPI
import com.example.heart_to_heart.domain.repository.PostRepository
import com.example.heart_to_heart.infrastructure.network.post_api.model.*
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.*
import java.io.File

class DefaultPostRepository
constructor(
    private val postAPI: PostAPI,
    private val sessionStorage: SessionStorage
) : PostRepository {

    override fun getPosts(page: Int, size: Int): Observable<GetAllPostsResult> {
        return Observable.create<GetAllPostsResult> { emitter ->
            val postService = postAPI.getPostService()
            postService.getPosts(page, size).enqueue(object: Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val gson = Gson()
                    if(response.isSuccessful) {
                        val successResponse = gson.fromJson<GetAllPostsSuccessResponse>(
                            response.body()?.string()!!,
                            GetAllPostsSuccessResponse::class.java
                        )
                        emitter.onNext(GetAllPostsResult.SUCCESS(GetAllPostsResultData(successResponse.data.posts)))
                    } else {
                        val failureResponse = gson.fromJson<GetAllPostsFailureResponse>(
                            response.errorBody()?.string()!!,
                            GetAllPostsFailureResponse::class.java
                        )
                        emitter.onNext(GetAllPostsResult.FAILURE(GetAllPostsError.UNKNOWN_ERROR))
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }
            })
        }
    }

    override fun createPost(content: String, uris: MutableList<Uri>): Observable<CreatePostResult> {

        if (sessionStorage.getUserId() == null) {
            return Observable.create<CreatePostResult> { emitter
                -> emitter.onNext(CreatePostResult.FAILURE(CreatePostError.NO_LOG_IN))
            }
        }

        val userId = sessionStorage.getUserId()!!

        return Observable.create<CreatePostResult> { emitter ->

            var bodies = uris.map {
                val path = it.path!!
                var file = File(path)
                var requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                MultipartBody.Part.createFormData("field", file.name, requestFile)
            }

            val contentBody = RequestBody.create(MediaType.parse("multipart/form-data"), content)
            var userIdBody = RequestBody.create(MediaType.parse("multipart/form-data"), userId)

            val postService = postAPI.getPostService()
            postService.createPost(bodies, contentBody, userIdBody).enqueue(object: Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val gson = Gson()
                    if(response.isSuccessful) {
                        val successResponse = gson.fromJson<CreatePostSuccessResponse>(
                            response.body()?.string()!!,
                            CreatePostSuccessResponse::class.java
                        )
                        Log.d("YOLO", successResponse.data.post.toString())
                        emitter.onNext(CreatePostResult.SUCCESS(CreatePostResultData(successResponse.data.post)))
                    } else {
                        val failureResponse = gson.fromJson<CreatePostFailureResponse>(
                            response.errorBody()?.string()!!,
                            CreatePostFailureResponse::class.java
                        )
                        when (failureResponse.code) {
                            -1 -> {
                                emitter.onNext(CreatePostResult.FAILURE(CreatePostError.UNKNOWN_ERROR))
                            }
                            else -> {
                                emitter.onNext(CreatePostResult.FAILURE(CreatePostError.UNKNOWN_ERROR))
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    emitter.onNext(CreatePostResult.FAILURE(CreatePostError.UNKNOWN_ERROR))
                }
            })
        }
    }
}