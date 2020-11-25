package com.example.heart_to_heart.data.repository

import android.util.Log
import com.example.heart_to_heart.data.repository.dataSource.remote.PostsAPI
import com.example.heart_to_heart.domain.repository.PostsRepository
import com.example.heart_to_heart.infrastructure.model.GetPostsFailureResponse
import com.example.heart_to_heart.infrastructure.model.GetPostsResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.*
import java.net.HttpURLConnection

class DefaultPostsRepository
constructor(
    private val postsAPI: PostsAPI
) : PostsRepository {

    override fun getAllPosts() {
        var postsService = postsAPI.getPostsService()
        postsService
            .getPosts()
            .enqueue(object :  Callback<GetPostsResponse> {
                override fun onResponse(
                    call: Call<GetPostsResponse>,
                    response: Response<GetPostsResponse>
                ) {
                    Log.d("YOLO", "onResponse() from DefaultPostsRepository")
                    if (response.isSuccessful) {
                        var getPostsResponse = response?.body()
                        Log.d("YOLO", "isSuccessful == true")
                        Log.d("YOLO", "CODE: ${getPostsResponse?.code}")
                        Log.d("YOLO", "MESSAGE: ${getPostsResponse?.message}")
                        Log.d("YOLO", "data: ${getPostsResponse?.data?.posts.toString()}")

                    } else {
                        Log.d("YOLO", "CODEE: ${response.code()}")
                        val errorResponseBody = response?.errorBody()
                        Log.d("YOLO", "isSuccessful == false")
                        val gson = Gson()
                        val getPostsFailureResponse = gson.fromJson<GetPostsFailureResponse>(errorResponseBody?.string()!!, GetPostsFailureResponse::class.java)
                        Log.d("YOLO", "code: ${getPostsFailureResponse.code}")
                        if(response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                            Log.d("YOLO", "REFRESH TOKEN HAS EXPIRED!!!!")
                            // LogOut
                        }
                    }
                }
                override fun onFailure(call: Call<GetPostsResponse>, t: Throwable) {
                    Log.d("YOLO", "onFailure() from DefaultPostsRepository~!~")
                    Log.d("YOLO", "MESSAGE: ${t.message!!}")
                }
            })
    }
}