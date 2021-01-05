package com.example.heart_to_heart.data.repository

import android.net.Uri
import android.util.Log
import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.example.heart_to_heart.data.repository.dataSource.remote.base.UserAPI
import com.example.heart_to_heart.domain.repository.UserRepository
import com.example.heart_to_heart.infrastructure.network.user_api.model.*
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class DefaultUserRepository : UserRepository {

    private val userAPI: UserAPI
    private val sessionStorage: SessionStorage

    constructor(userAPI: UserAPI, sessionStorage: SessionStorage) {
        this.userAPI = userAPI
        this.sessionStorage = sessionStorage
    }

    override fun getUserInfo(): Observable<Boolean> {
        Log.d("YOLO", "getUserInfo() from DefaultUserRepository")
        return Observable.create<Boolean>() {
            it.onNext(true)
        }
    }

    override fun uploadAvatarImage(imageUri: Uri): Observable<UploadAvatarImageResult> {

        if (sessionStorage.getUserId() == null) {
            return Observable.create<UploadAvatarImageResult> { emitter ->
                emitter.onNext(UploadAvatarImageResult.FAILURE(UploadAvatarImageError.NO_USER_ID))
            }
        }

        return Observable.create<UploadAvatarImageResult>() { emitter ->
            val userId = sessionStorage.getUserId()!!
            var userIdBody = RequestBody.create(MediaType.parse("multipart/form-data"), userId)

            val path = imageUri.path!!
            val file = File(path)
            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val imageBody = MultipartBody.Part.createFormData("field", file.name, requestFile)

            val userService = userAPI.getUserService()
            userService.uploadAvatarImage(userIdBody, imageBody).enqueue(object: Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val gson = Gson()
                    if(response.isSuccessful) {
                        val successResponse = gson.fromJson<UploadAvatarImageSuccessResponse>(
                            response.body()?.string()!!,
                            UploadAvatarImageSuccessResponse::class.java
                        )
                        emitter.onNext(UploadAvatarImageResult.SUCCESS(UploadAvatarImageResultData(successResponse.data.uri)))
                    } else {
                        val failureResponse = gson.fromJson<UploadAvatarImageFailureResponse>(
                            response.errorBody()?.string()!!,
                            UploadAvatarImageFailureResponse::class.java
                        )
                        emitter.onNext(UploadAvatarImageResult.FAILURE(UploadAvatarImageError.UNKNOWN_ERROR))
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    emitter.onNext(UploadAvatarImageResult.FAILURE(UploadAvatarImageError.NETWORK_CONNECTION_ERROR))
                }
            })
        }
    }
}