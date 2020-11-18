package com.example.heart_to_heart.presentation.screen.authorization.signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.domain.usecase.SignUpUseCase
import com.example.heart_to_heart.infrastructure.model.SignUpError
import com.example.heart_to_heart.infrastructure.model.SignUpResponse
import com.example.heart_to_heart.infrastructure.model.SignUpResult
import com.example.heart_to_heart.presentation.base.BaseViewModel
import com.example.heart_to_heart.presentation.screen.AppRouter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class SignUpViewModel
constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {

    var routingEvent: MutableLiveData<SignUpVMRoutingEvent?> = MutableLiveData<SignUpVMRoutingEvent?>(null)

    val email: MutableLiveData<String> = MutableLiveData("")
    var firstName: MutableLiveData<String> = MutableLiveData("")
    var lastName: MutableLiveData<String> = MutableLiveData("")
    var nickname: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    val emailErrorText: MutableLiveData<String?> = MutableLiveData("")
    var firstNameErrorText: MutableLiveData<String?> = MutableLiveData("")
    var lastNameErrorText: MutableLiveData<String?> = MutableLiveData("")
    var nicknameErrorText: MutableLiveData<String?> = MutableLiveData("")
    var passwordErrorText: MutableLiveData<String?> = MutableLiveData("")

    fun logIn() {
        routingEvent.value = SignUpVMRoutingEvent.CLOSE_SIGN_UP
    }

    fun signUp() {
        if(!validateInputs()) {
            Log.d("YOLO", "INVALID INPUTS")
            return
        }
        Log.d("YOLO", "VALID INPUTS")
    }

    private fun validateInputs(): Boolean {
        if (!validateEmail() or !validateFirstName() or !validateLastName() or !validateNickname() or !validatePassword()) {
            return false
        }
        return true
    }

    private fun validateEmail(): Boolean {
        if(email.value?.trim().isNullOrEmpty()) {
            emailErrorText.value = "Email Field Can't be empty."
            return false
        } else {
            emailErrorText.value = null
            return true
        }
    }

    private fun validateFirstName(): Boolean {
        if(firstName.value?.trim().isNullOrEmpty()) {
            firstNameErrorText.value = "Password Field Can't be empty."
            return false
        } else {
            firstNameErrorText.value = null
            return true
        }
    }

    private fun validateLastName(): Boolean {
        if(lastName.value?.trim().isNullOrEmpty()) {
            lastNameErrorText.value = "Password Field Can't be empty."
            return false
        } else {
            lastNameErrorText.value = null
            return true
        }
    }

    private fun validateNickname(): Boolean {
        if(nickname.value?.trim().isNullOrEmpty()) {
            nicknameErrorText.value = "Password Field Can't be empty."
            return false
        } else {
            nicknameErrorText.value = null
            return true
        }
    }

    private fun validatePassword(): Boolean {
        if(password.value?.trim().isNullOrEmpty()) {
            passwordErrorText.value = "Password Field Can't be empty."
            return false
        } else {
            passwordErrorText.value = null
            return true
        }
    }

//    fun signUp(email: String, firstName: String, lastName: String, nickname: String, password: String) {
//        signUpUseCase.email = email
//        signUpUseCase.firstName = firstName
//        signUpUseCase.lastName = lastName
//        signUpUseCase.nickname = nickname
//        signUpUseCase.password = password
//
//        signUpUseCase.execute().subscribe({ signUpResult ->
//            when (signUpResult) {
//                is SignUpResult.SUCCESS -> {
//                    Log.d("YOLO", "SUCCESSFULLY SIGNED UP.")
//                    didRouterChange.onNext(SignUpFragmentRoutingOptions.CLOSE)
//                }
//                is SignUpResult.FAILURE -> {
//                    when (signUpResult.error) {
//                        SignUpError.ALREADY_EXISTED_EMAIL -> {
//                            Log.d("YOLO", "ALREADY SIGNED UP..")
//                            didRouterChange.onNext(SignUpFragmentRoutingOptions.SHOW_ALREADY_SIGNED_UP)
//                        }
//                        SignUpError.NETWORK_CONNECTION_ERROR -> {
//                            Log.d("YOLO", "NETWORK ERROR.")
//                            didRouterChange.onNext(SignUpFragmentRoutingOptions.SHOW_NETWORK_ERROR)
//                        }
//                    }
//                }
//            }
//        }, {
//            Log.d("YOLO", "Network Error")
//            Log.d("YOLO", it.toString())
//        }, {
//            Log.d("YOLO", "onComplete")
//        }).apply { disposables.add(this) }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        disposables.clear()
//    }
}

