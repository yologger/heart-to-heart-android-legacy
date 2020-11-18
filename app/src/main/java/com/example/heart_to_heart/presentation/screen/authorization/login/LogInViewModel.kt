package com.example.heart_to_heart.presentation.screen.authorization.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.LogInUseCase
import com.example.heart_to_heart.presentation.base.BaseViewModel

enum class LogInFragmentRoutingAction {
    SHOW_SIGN_UP,
    SHOW_FIND_PASSWORD
}

class LogInViewModel
constructor(
    private val logInUseCase: LogInUseCase
) : BaseViewModel() {

    var routingEvent: MutableLiveData<LogInVMRoutingEvent?> = MutableLiveData(null)

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    val emailErrorText: MutableLiveData<String?> = MutableLiveData("")
    var passwordErrorText: MutableLiveData<String?> = MutableLiveData("")

    fun logIn() {
        if(!validateInputs()) { return }
        Log.d("YOLO", "VALID INPUTS")
    }

    fun signUp() {
        routingEvent.value = LogInVMRoutingEvent.SHOW_SIGN_UP
    }

    private fun validateInputs(): Boolean {
        if (!validateEmail() or !validatePassword()) {
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

    private fun validatePassword(): Boolean {
        if(password.value?.trim().isNullOrEmpty()) {
            passwordErrorText.value = "Password Field Can't be empty."
            return false
        } else {
            passwordErrorText.value = null
            return true
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}


//import android.util.Log
//import com.example.heart_to_heart.domain.usecase.LogInUseCase
//import com.example.heart_to_heart.infrastructure.model.LogInError
//import com.example.heart_to_heart.infrastructure.model.LogInResult
//import com.example.heart_to_heart.presentation.base.BaseViewModel
//import io.reactivex.subjects.PublishSubject
//
//class LogInViewModel
//constructor(
//    private val logInUseCase: LogInUseCase
//) : BaseViewModel() {
//
//    val didRoutingChange = PublishSubject.create<LogInFragmentRoutingOption>()
//
//    fun logIn(email: String, password: String) {
//        logInUseCase.email = email
//        logInUseCase.password = password
//
//        logInUseCase.execute().subscribe({ logInResult ->
//            when(logInResult) {
//                is LogInResult.SUCCESS -> {
//                    didRoutingChange.onNext(LogInFragmentRoutingOption.SHOW_MAIN)
//                }
//                is LogInResult.FAILURE -> {
//                    when(logInResult.error) {
//                        LogInError.NETWORK_CONNECTION_ERROR -> {
//                            didRoutingChange.onNext(LogInFragmentRoutingOption.NETWORK_CONNECTION_ERROR)
//                        }
//                        LogInError.INVALID_EMAIL -> {
//                            didRoutingChange.onNext(LogInFragmentRoutingOption.INVALID_EMAIL)
//                        }
//                        LogInError.INVALID_PASSWORD -> {
//                            didRoutingChange.onNext(LogInFragmentRoutingOption.INVALID_PASSWORD)
//                        }
//                    }
//                }
//            }
//        }, {
//            Log.d("YOLO", "ERROR")
//        }, {
//            Log.d("YOLO", "COMPLETED.")
//        }, {
//            Log.d("YOLO", "DISPOED.")
//        }).apply { disposables.add(this) }
//    }
//}