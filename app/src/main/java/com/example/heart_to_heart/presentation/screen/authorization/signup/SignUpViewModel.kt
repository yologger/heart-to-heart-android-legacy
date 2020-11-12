package com.example.heart_to_heart.presentation.screen.authorization.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.model.SignUpData
import com.example.heart_to_heart.domain.usecase.SignUpUseCase
import com.example.heart_to_heart.infrastructure.model.SignUpError
import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import io.reactivex.subjects.PublishSubject

class SignUpViewModel
constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    val didRouterChange = PublishSubject.create<SignUpFragmentRoutingOptions>()

    fun test() {
        Log.d("YOLO", "test() from SignUpViewModel")
        this.signUpUseCase.execute()
    }

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

        signUpUseCase.execute().subscribe(
            { signUpResponse ->
                when(signUpResponse) {
                    is SignUpResponse.SUCCESS -> {
                        Log.d("YOLO", "SUCCESSFULLY SIGNED UP.")
                        didRouterChange.onNext(SignUpFragmentRoutingOptions.CLOSE)
                    }
                    is SignUpResponse.FAILURE -> {
                        when(signUpResponse.error) {
                            SignUpError.ALREADY_SIGNED_UP_EMAIL -> {
                                Log.d("YOLO", "ALREADY SIGNED UP..")
                                didRouterChange.onNext(SignUpFragmentRoutingOptions.SHOW_ALREADY_SIGNED_UP)
                            }
                            SignUpError.NETWORK_ERROR -> {
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
            }
        )
    }
}

