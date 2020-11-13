package com.example.heart_to_heart.data.repository

import android.util.Log
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.infrastructure.model.*
import com.example.heart_to_heart.infrastructure.network.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.*


class DefaultAuthorizationRepository
constructor(
    private val defaultAuthorizationApi: DefaultAuthorizationApi,
    private val retrofit: Retrofit
) : AuthorizationRepository {

    override fun signUp(
        email: String,
        firstName: String,
        lastName: String,
        nickname: String,
        password: String
    ): Observable<SignUpResult> {
        val signUpRequest = SignUpRequest(email, firstName, lastName, nickname, password)
        return Observable.create<SignUpResult> { emitter ->
            defaultAuthorizationApi.signUp(signUpRequest).enqueue(object : Callback<SignUpResponse> {
                override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                    if (response.isSuccessful) {
                        emitter.onNext(SignUpResult.SUCCESS)
                    } else {
                        val errorBody = response.errorBody()!!
                        val converter = retrofit.responseBodyConverter<SignUpFailureResponse>(SignUpFailureResponse::class.java, SignUpFailureResponse::class.java.annotations)
                        val signUpFailureResponse = converter.convert(errorBody)
                        emitter.onNext(SignUpResult.FAILURE(SignUpError.ALREADY_EXISTED_EMAIL))
                    }
                }
                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    emitter.onNext(SignUpResult.FAILURE(SignUpError.NETWORK_CONNECTION_ERROR))
                }
            })
        }
    }

    override fun logIn(email: String, password: String): Observable<LogInResult> {
        val logInRequest = LogInRequest(email, password)
        return Observable.create<LogInResult> { emitter ->
            defaultAuthorizationApi.logIn(logInRequest).enqueue(object : Callback<LogInResponse> {

                override fun onResponse(
                    call: Call<LogInResponse>,
                    response: Response<LogInResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("YOLO", "11111")
                        Log.d("YOLO", "code: ${response.code()}")
                        Log.d("YOLO", "isSuccessful: ${response.isSuccessful}")
                        Log.d("YOLO", "${response.body()?.message}")
                        Log.d("YOLO", "${response.body()?.data?.email}")
                        Log.d("YOLO", "${response.body()?.data?.nickname}")
                        Log.d("YOLO", "${response.body()?.data?.accessToken}")
                        Log.d("YOLO", "${response.body()?.data?.refreshToken}")
                        emitter.onNext(LogInResult.SUCCESS)
                    } else {
                        val errorBody = response.errorBody()!!
                        val converter = retrofit.responseBodyConverter<LogInFailureResponse>(LogInFailureResponse::class.java, LogInFailureResponse::class.java.annotations)
                        val logInFailureResponse = converter.convert(errorBody)
                        Log.d("YOLO", "${logInFailureResponse?.code}")
                        Log.d("YOLO", "${logInFailureResponse?.errorMessage}")
                        emitter.onNext(LogInResult.FAILURE(LogInError.INVALID_EMAIL))
                    }
                }

                override fun onFailure(call: Call<LogInResponse>, error: Throwable) {
                    emitter.onNext(LogInResult.FAILURE(LogInError.NETWORK_CONNECTION_ERROR))
                }

            })
        }
    }
}