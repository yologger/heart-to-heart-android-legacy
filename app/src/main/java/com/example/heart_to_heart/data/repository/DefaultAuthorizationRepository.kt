package com.example.heart_to_heart.data.repository

import android.util.Log
import com.example.heart_to_heart.data.model.Profile
import com.example.heart_to_heart.data.model.Session
import com.example.heart_to_heart.data.model.Tokens
import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.example.heart_to_heart.data.repository.dataSource.remote.AuthorizationAPI
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.*
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.*

class DefaultAuthorizationRepository
constructor(
    private val authorizationAPI: AuthorizationAPI,
    private val sessionStorage: SessionStorage
) : AuthorizationRepository {

    override fun signUp(email: String, firstName: String, lastName: String, nickname: String, password: String): Observable<SignUpResult> {
        val signUpRequest = SignUpRequest(email, firstName, lastName, nickname, password)
        val signUpService = authorizationAPI.getSignUpService()

        return Observable.create<SignUpResult> { emitter ->
            signUpService
                .signUp(signUpRequest)
                .enqueue(object : Callback<SignUpResponse> {
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        if (response.isSuccessful) {
                            emitter.onNext(SignUpResult.SUCCESS)
                        } else {
                            val errorResponseBody = response?.errorBody()
                            val gson = Gson()
                            var signUpFailureResponse = gson.fromJson(
                                errorResponseBody?.string()!!,
                                SignUpFailureResponse::class.java
                            )
                            when (signUpFailureResponse.code) {
                                -1 -> {
                                    emitter.onNext(SignUpResult.FAILURE(SignUpError.ALREADY_EXISTED_EMAIL))
                                }
                                else -> {
                                    emitter.onNext(SignUpResult.FAILURE(SignUpError.UNKNOWN_ERROR))
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                        emitter.onNext(SignUpResult.FAILURE(SignUpError.NETWORK_CONNECTION_ERROR))
                    }
                })
        }
    }

    override fun logIn(email: String, password: String): Observable<LogInResult> {

        val logInService = authorizationAPI.getLogInService()
        val logInRequest = LogInRequest(email, password)

        return Observable.create<LogInResult> { emitter ->
            logInService.logIn(logInRequest).enqueue(object : Callback<LogInResponse> {
                override fun onResponse(
                    call: Call<LogInResponse>,
                    response: Response<LogInResponse>
                ) {
                    if (response.isSuccessful) {
                        val accessToken = response.body()?.data?.accessToken!!
                        val refreshToken = response.body()?.data?.refreshToken!!
                        val tokens = Tokens(accessToken, refreshToken)

                        var email = response.body()?.data?.email!!
                        var userId = response.body()?.data?.userId!!
                        var nickname = response.body()?.data?.nickname!!

                        var profile = Profile(email, nickname, userId)
                        var session = Session(email, profile, tokens)
                        setSession(session)
                        emitter.onNext(LogInResult.SUCCESS)
                    } else {
                        val errorResponseBody = response?.errorBody()
                        val gson = Gson()
                        val logInFailureResponse = gson.fromJson<LogInFailureResponse>(
                            errorResponseBody?.string()!!,
                            LogInFailureResponse::class.java
                        )
                        when (logInFailureResponse.code) {
                            -1 -> {
                                emitter.onNext(LogInResult.FAILURE(LogInError.INVALID_EMAIL))
                            }
                            -2 -> {
                                emitter.onNext(LogInResult.FAILURE(LogInError.INVALID_PASSWORD))
                            }
                            else -> {
                                emitter.onNext(LogInResult.FAILURE(LogInError.UNKNOWN_ERROR))
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<LogInResponse>, error: Throwable) {
                    emitter.onNext(LogInResult.FAILURE(LogInError.NETWORK_CONNECTION_ERROR))
                }

            })
        }
    }

    override fun logOut(): Observable<Boolean> {
        val logOutService = authorizationAPI.getLogOutService()
        return Observable.create<Boolean> { emitter ->
            if (sessionStorage.getSession() == null) {
                emitter.onNext(false)
            } else {
                var accessToken = sessionStorage.getAccessToken()!!
                logOutService.logOut("Bearer $accessToken")
                    .enqueue(object : Callback<LogOutResponse> {
                        override fun onResponse(
                            call: Call<LogOutResponse>,
                            response: Response<LogOutResponse>
                        ) {
                            Log.d("YOLO", "///////////////////////////////////////////////////////")
                            if (response.isSuccessful) {
                                // (VALID ACCESS TOKEN) or (INVALID ACCESS TOKEN && VALID REFRESH TOKEN)
                                Log.d("YOLO", "LOGOUT REQUEST SUCCESS")
                                removeSession()
                                emitter.onNext(true)
                            } else {
                                // (INVALID REFRESH TOKEN)
                                Log.d("YOLO", "LOGOUT REQUEST FAILURE")
                                var errorResponseBody = response?.errorBody()
                                val gson = Gson()
                                var logOutFailureResponse = gson.fromJson<LogOutFailureResponse>(errorResponseBody?.string()!!, LogOutFailureResponse::class.java)
                                Log.d("YOLO", "MESSAGE: ${logOutFailureResponse?.errorMessage}")
                                Log.d("YOLO", "CODE: ${logOutFailureResponse?.code}")
                                when (logOutFailureResponse?.code) {
//                                    -1 -> { Log.d("YOLO", "Access Token Error / No Authorization Header") }
//                                    -2 -> { Log.d("YOLO", "Access Token Error / Authorization Header doesn't start with Bearer") }
//                                    -3 -> { Log.d("YOLO", "Access Token Error / Invalid Access Token") }
//                                    -4 -> { Log.d("YOLO", "Access Token Error / Access Token has expired") }
//                                    -5 -> { Log.d("YOLO", "Access Token Error / Invalid Access Token. User does not exists") }
//                                    -6 -> { Log.d("YOLO", "Access Token Error / Invalid Access Token. User already has logged out") }
//                                    -7 -> { Log.d("YOLO", "Access Token Error / Invalid Access Token. Old Access Token") }
                                    -11 -> { Log.d("YOLO", "Refresh Token Error / No Refresh Token in request body") }
                                    -12 -> { Log.d("YOLO", "Refresh Token Error / Invalid Refresh Token") }
                                    -13 -> { Log.d("YOLO", "Refresh Token Error / Refresh Token has expired") }
                                    -14 -> { Log.d("YOLO", "Refresh Token Error / Invalid Refresh Token. User does not exists") }
                                    -15 -> { Log.d("YOLO", "Refresh Token Error / Invalid Refresh Token. User already has logged out") }
                                    -16 -> { Log.d("YOLO", "Refresh Token Error / Invalid Refresh Token. Old Refresh Token") }
                                    else -> { Log.d("YOLO", "Unknown Error") }
                                }
                            }
                            // emitter.onNext(false)
                        }

                        override fun onFailure(call: Call<LogOutResponse>, t: Throwable) {
                            emitter.onNext(false)
                        }
                    })
            }
        }
    }

    override fun validateSession(): Observable<ValidateSessionResult> {
        val currentSession = sessionStorage.getSession() ?: return Observable.create { it.onNext(ValidateSessionResult.FAILURE(ValidateSessionError.EXPIRED_ACCESS_TOKEN)) }

        return Observable.create { emitter ->
            val validateSessionService = authorizationAPI.getValidateSessionService()
            validateSessionService.validateAccessToken().enqueue(object: Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val gson = Gson()
                        val successResponse = gson.fromJson<ValidateSessionSuccessResponse>(
                            response.body()?.string()!!,
                            ValidateSessionSuccessResponse::class.java
                        )
                        emitter.onNext(ValidateSessionResult.SUCCESS)
                    } else {
                        val gson = Gson()
                        val failureResponse = gson.fromJson<ValidateSessionFailureResponse>(
                            response?.errorBody()?.string()!!,
                            ValidateSessionFailureResponse::class.java
                        )
                        emitter.onNext(ValidateSessionResult.FAILURE(ValidateSessionError.EXPIRED_REFRESH_TOKEN))
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    emitter.onNext(ValidateSessionResult.FAILURE(ValidateSessionError.NETWORK_ERROR))
                }
            })
        }
    }

    override fun getSessionState(): Observable<Boolean> {
        return sessionStorage.getSessionState()
    }

    private fun setSession(session: Session) = sessionStorage.setSession(session)
    override fun removeSession() = sessionStorage.removeSession()
}