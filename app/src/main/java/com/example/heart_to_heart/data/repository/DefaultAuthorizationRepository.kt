package com.example.heart_to_heart.data.repository

import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.infrastructure.model.SignUpError
import com.example.heart_to_heart.infrastructure.model.SignUpRequest
import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import com.example.heart_to_heart.infrastructure.network.DefaultAuthorizationApi
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DefaultAuthorizationRepository
constructor(
    private val defaultAuthorizationApi: DefaultAuthorizationApi
) : AuthorizationRepository {

    override fun signUp(
        email: String,
        firstName: String,
        lastName: String,
        nickname: String,
        password: String
    ): Observable<SignUpResponse> {
        val signUpRequest = SignUpRequest(email, firstName, lastName, nickname, password)
        return Observable.create<SignUpResponse> { emitter ->
            defaultAuthorizationApi
                .signUp(signUpRequest).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
//                            Log.d("YOLO", "CODE: ${response.code()}")
//                            Log.d("YOLO", "BODY: ${response.body()?.string()}")
                            emitter.onNext(SignUpResponse.SUCCESS)
                        } else {
//                            Log.d("YOLO", "CODE: ${response.code()}")
//                            Log.d("YOLO", "ERROR BODY: ${response.errorBody()?.string()}")
                            emitter.onNext(SignUpResponse.FAILURE(SignUpError.ALREADY_SIGNED_UP_EMAIL))
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
                        emitter.onNext(SignUpResponse.FAILURE(SignUpError.NETWORK_ERROR))
                    }
                })
        }
    }
}
