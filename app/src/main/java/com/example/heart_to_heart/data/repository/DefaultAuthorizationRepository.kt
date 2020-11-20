package com.example.heart_to_heart.data.repository

import android.util.Log
import com.example.heart_to_heart.data.model.Profile
import com.example.heart_to_heart.data.model.Session
import com.example.heart_to_heart.data.model.Tokens
import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.example.heart_to_heart.data.repository.dataSource.remote.AuthorizationAPI
import com.example.heart_to_heart.domain.repository.AuthorizationRepository
import com.example.heart_to_heart.infrastructure.model.*
import com.example.heart_to_heart.infrastructure.network.*

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import retrofit2.*

class DefaultAuthorizationRepository
constructor(
    private var authorizationAPI: AuthorizationAPI,
    private val retrofit: Retrofit,
    private val sessionStorage: SessionStorage
) : AuthorizationRepository {

    private var name = "String"
    private var _session: Session? = null
    private var sessionState: BehaviorSubject<Boolean>

    var didLogIn = PublishSubject.create<Unit>()
    var didLogOut = PublishSubject.create<Unit>()

    init {
        this.loadSession()
        if (_session == null) {
            sessionState = BehaviorSubject.createDefault<Boolean>(false)
        } else {
            sessionState = BehaviorSubject.createDefault<Boolean>(true)
        }
    }

    override fun getSession(): Observable<Boolean> = sessionState.hide()

    override fun signUp(email: String, firstName: String, lastName: String, nickname: String, password: String): Observable<SignUpResult> {
        var signUpService = authorizationAPI.getSignUpService()
        val signUpRequest = SignUpRequest(email, firstName, lastName, nickname, password)
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

        var logInService = authorizationAPI.getLogInService()
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
        val logOutService = authorizationAPI.getLogOutService()
        return Observable.create<Boolean> { emitter ->
            if (sessionState == null) {
                emitter.onNext(false)
            } else {
                val accessToken = _session?.tokens?.accessToken
                logOutService.logOut("Bearer $accessToken")
                    .enqueue(object : Callback<LogOutResponse> {
                        override fun onResponse(
                            call: Call<LogOutResponse>,
                            response: Response<LogOutResponse>
                        ) {
                            if (response.isSuccessful) {
                                removeSession()
                                emitter.onNext(true)
                                Log.d("YOLO", "MESSAGE: HERE")
                            } else {
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
                                when (logOutFailureResponse?.code) {
                                    -1 -> {
                                        Log.d("YOLO", "No Authorization Header")
                                    }
                                    -2 -> {
                                        Log.d(
                                            "YOLO",
                                            "Authorization Header must starts with Bearer."
                                        )
                                    }
                                    -3 -> {
                                        Log.d("YOLO", "Invalid Access Token")
                                    }
                                    -4 -> {
                                        Log.d("YOLO", "Expired Access Token")
                                        // Reissue AccessToken
                                    }
                                    -5 -> {
                                        Log.d("YOLO", "Invalid Access Token. User doesn't exist.")
                                    }
                                    -6 -> {
                                        Log.d("YOLO", "Access token has already been logged out.")
                                    }
                                    -7 -> {
                                        Log.d("YOLO", "Old Access Token")
                                    }
                                    else -> {
                                        Log.d("YOLO", "Unknown Error")
                                    }
                                }
                            }
                            emitter.onNext(false)
                        }

                        override fun onFailure(call: Call<LogOutResponse>, t: Throwable) {
                            emitter.onNext(false)
                        }
                    })
            }
        }
    }

    private fun loadSession() {
        _session = sessionStorage.getSession()
    }

    fun setSession(session: Session) {
        _session = session
        sessionStorage.setSession(session)
    }

    fun removeSession() {
        sessionStorage.removeSession()
        _session = null
    }
}