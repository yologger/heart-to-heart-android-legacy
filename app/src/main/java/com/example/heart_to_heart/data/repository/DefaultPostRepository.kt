package com.example.heart_to_heart.data.repository

import android.net.Uri
import android.util.Log
import com.example.heart_to_heart.data.repository.dataSource.remote.PostsAPI
import com.example.heart_to_heart.domain.repository.PostRepository
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostError
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostFailureResponse
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostResult
import com.example.heart_to_heart.infrastructure.network.post_api.model.CreatePostSuccessResponse
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
    private val postsAPI: PostsAPI
) : PostRepository {

    override fun getAllPosts() {

    }

    override fun createPost(content: String, uris: MutableList<Uri>): Observable<CreatePostResult> {
        return Observable.create<CreatePostResult> { emitter ->

            var bodies = uris.map {
                val path = it.path!!
                var file = File(path)
                var requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                MultipartBody.Part.createFormData("field", file.name, requestFile)
            }

            val contentBody = RequestBody.create(MediaType.parse("multipart/form-data"), content)

            val postsService = postsAPI.getPostsService()
            postsService.post(bodies, contentBody).enqueue(object: Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val gson = Gson()
                    if(response.isSuccessful) {
                        val createPostSuccessResponse = gson.fromJson<CreatePostSuccessResponse>(
                            response.body()?.string()!!,
                            CreatePostSuccessResponse::class.java
                        )
                        emitter.onNext(CreatePostResult.SUCCESS)
                    } else {
                        val createPostFailureResponse = gson.fromJson<CreatePostFailureResponse>(
                            response.errorBody()?.string()!!,
                            CreatePostFailureResponse::class.java
                        )
                        when (createPostFailureResponse.code) {
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