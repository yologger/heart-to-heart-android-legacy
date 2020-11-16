package com.example.heart_to_heart.data.repository

import android.util.Log
import com.example.heart_to_heart.data.model.Profile
import com.example.heart_to_heart.data.model.Session
import com.example.heart_to_heart.data.model.Tokens
import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.infrastructure.model.*
import com.example.heart_to_heart.infrastructure.network.DefaultAuthorizationApi
import com.example.heart_to_heart.infrastructure.network.LogOutFailureResponse
import com.example.heart_to_heart.infrastructure.network.LogOutResponse

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.PublishSubject
import retrofit2.*

class DefaultAuthorizationRepository
constructor(
    private val defaultAuthorizationApi: DefaultAuthorizationApi,
    private val retrofit: Retrofit,
    private val sessionStorage: SessionStorage
) : AuthorizationRepository {

    var tokens: Tokens? = null
    var sessionState: Session? = null

    var didLogIn = PublishSubject.create<Unit>()
    var didLogOut = PublishSubject.create<Unit>()

    override fun getSession(): Session? = this.sessionState

    override fun getLogInEvent(): Observable<Unit> = didLogIn.hide()!!
    override fun getLogOutEvent(): Observable<Unit> = didLogOut.hide()!!

    init { this.loadSession() }

    override fun signUp(
        email: String,
        firstName: String,
        lastName: String,
        nickname: String,
        password: String
    ): Observable<SignUpResult> {
        val signUpRequest = SignUpRequest(email, firstName, lastName, nickname, password)
        return Observable.create<SignUpResult> { emitter ->
            defaultAuthorizationApi.signUp(signUpRequest)
                .enqueue(object : Callback<SignUpResponse> {
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        if (response.isSuccessful) {
                            emitter.onNext(SignUpResult.SUCCESS)
                        } else {
                            val errorBody = response.errorBody()!!
                            val converter = retrofit.responseBodyConverter<SignUpFailureResponse>(
                                SignUpFailureResponse::class.java,
                                SignUpFailureResponse::class.java.annotations
                            )
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
                    Log.d("YOLO", "code: ${response.code()}")
                    if (response.isSuccessful) {
                        Log.d("YOLO", "${response.body()?.message}")

                        val accessToken = response.body()?.data?.accessToken!!
                        val refreshToken = response.body()?.data?.refreshToken!!
                        val tokens = Tokens(accessToken, refreshToken)
                        setToken(tokens)

                        var email = response.body()?.data?.email!!
                        var userId = response.body()?.data?.userId!!
                        var nickname = response.body()?.data?.nickname!!

                        var profile = Profile(email, nickname, userId)
                        var session = Session(email, profile, tokens)
                        setSession(session)

                        emitter.onNext(LogInResult.SUCCESS)
                    } else {
                        val errorBody = response.errorBody()!!
                        val converter = retrofit.responseBodyConverter<LogInFailureResponse>(
                            LogInFailureResponse::class.java,
                            LogInFailureResponse::class.java.annotations
                        )
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

    override fun logOut(): Observable<Boolean> {
        Log.d("YOLO", "logOut() from DefaultAuthorizationRepository")
        return Observable.create<Boolean> { emitter ->
            if (sessionState == null) {
                emitter.onNext(false)
            } else {
                val accessToken = tokens?.accessToken!!
                defaultAuthorizationApi.logOut("Bearer $accessToken")
                    .enqueue(object : Callback<LogOutResponse> {
                        override fun onResponse(
                            call: Call<LogOutResponse>,
                            response: Response<LogOutResponse>
                        ) {
                            if (response.isSuccessful) {
                                removeTokens()
                                removeSession()
                                didLogOut.onNext(Unit)
                            } else {
                                removeTokens()
                                removeSession()
                                val errorBody = response.errorBody()!!
                                val converter =
                                    retrofit.responseBodyConverter<LogOutFailureResponse>(
                                        LogOutFailureResponse::class.java,
                                        LogOutFailureResponse::class.java.annotations
                                    )
                                val logOutFailureResponse = converter.convert(errorBody)
                                Log.d("YOLO", "FAILURE")
                                Log.d("YOLO", "MESSAGE: ${logOutFailureResponse?.message}")
                                Log.d("YOLO", "CODE: ${logOutFailureResponse?.code}")
                            }
                            emitter.onNext(true)
                        }

                        override fun onFailure(call: Call<LogOutResponse>, t: Throwable) {

                            Log.d("YOLO", "ERROR")
                            emitter.onNext(false)
                        }
                    })
            }
        }
    }

    private fun loadSession() {
        this.sessionState = this.sessionStorage.getSession()
        this.tokens = this.sessionState?.tokens
    }

    fun setToken(tokens: Tokens) {
        this.tokens = tokens
    }

    fun setSession(session: Session) {
        this.sessionState = session
        this.sessionStorage.setSession(session)
    }

    fun removeTokens() {
        Log.d("YOLO", "removeTokens()")
        tokens = null
    }

    fun removeSession() {
        Log.d("YOLO", "removeSession()")
        sessionStorage.removeSession()
        sessionState = null
    }
}