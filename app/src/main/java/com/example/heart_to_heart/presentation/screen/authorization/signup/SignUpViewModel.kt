package com.example.heart_to_heart.presentation.screen.authorization.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.usecase.SignUpUseCase
import com.example.heart_to_heart.infrastructure.model.SignUpError
import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import com.example.heart_to_heart.infrastructure.model.SignUpResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class SignUpViewModel
constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val disposables by lazy { CompositeDisposable() }
    val didRouterChange = PublishSubject.create<SignUpFragmentRoutingOptions>()

    fun signUp(
        email: String,
        firstName: String,
        lastName: String,
        nickname: String,
        password: String
    ) {
        signUpUseCase.email = email
        signUpUseCase.firstName = firstName
        signUpUseCase.lastName = lastName
        signUpUseCase.nickname = nickname
        signUpUseCase.password = password

        signUpUseCase.execute().subscribe({ signUpResult ->
            when (signUpResult) {
                is SignUpResult.SUCCESS -> {
                    Log.d("YOLO", "SUCCESSFULLY SIGNED UP.")
                    didRouterChange.onNext(SignUpFragmentRoutingOptions.CLOSE)
                }
                is SignUpResult.FAILURE -> {
                    when (signUpResult.error) {
                        SignUpError.ALREADY_EXISTED_EMAIL -> {
                            Log.d("YOLO", "ALREADY SIGNED UP..")
                            didRouterChange.onNext(SignUpFragmentRoutingOptions.SHOW_ALREADY_SIGNED_UP)
                        }
                        SignUpError.NETWORK_CONNECTION_ERROR -> {
                            Log.d("YOLO", "NETWORK ERROR.")
                            didRouterChange.onNext(SignUpFragmentRoutingOptions.SHOW_NETWORK_ERROR)
                        }
                    }
                }
            }
        }, {
            Log.d("YOLO", "Network Error")
            Log.d("YOLO", it.toString())
        }, {
            Log.d("YOLO", "onComplete")
        }).apply { disposables.add(this) }
    }
}

