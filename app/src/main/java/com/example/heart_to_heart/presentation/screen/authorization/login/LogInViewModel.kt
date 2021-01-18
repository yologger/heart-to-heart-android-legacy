package com.example.heart_to_heart.presentation.screen.authorization.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.LogInUseCase
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.LogInError
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.LogInResult
import com.example.heart_to_heart.presentation.base.BaseViewModel

class LogInViewModel
constructor(
    private val logInUseCase: LogInUseCase
) : BaseViewModel() {

    val routingEvent: MutableLiveData<LogInVMRoutingEvent?> = MutableLiveData(null)

    val email: MutableLiveData<String> = MutableLiveData("")
    // val email: MutableLiveData<String> = MutableLiveData("ronaldo@gmail.com")
    val emailErrorText: MutableLiveData<String?> = MutableLiveData("")

    val password: MutableLiveData<String> = MutableLiveData("")
    val passwordErrorText: MutableLiveData<String?> = MutableLiveData("")

    fun logIn() {
        if (!validateInputs()) {
            return
        }
        logInUseCase.email = email.value ?: ""
        logInUseCase.password = password.value ?: ""
        logInUseCase.execute().subscribe(
            { logInResult: LogInResult ->
                when (logInResult) {
                    is LogInResult.SUCCESS -> {
                        routingEvent.value = LogInVMRoutingEvent.SHOW_MAIN
                    }
                    is LogInResult.FAILURE -> {
                        when (logInResult.error) {
                            LogInError.INVALID_EMAIL -> {
                                routingEvent.value = LogInVMRoutingEvent.INVALID_EMAIL_ERROR
                            }
                            LogInError.INVALID_PASSWORD -> {
                                routingEvent.value = LogInVMRoutingEvent.INVALID_PASSWORD_ERROR
                            }
                            LogInError.NETWORK_CONNECTION_ERROR -> {
                                routingEvent.value = LogInVMRoutingEvent.NETWORK_CONNECTION_ERROR
                            }
                            LogInError.UNKNOWN_ERROR -> {
                                routingEvent.value = LogInVMRoutingEvent.UNKNOWN_ERROR
                            }
                        }
                    }
                }
            }, {
                routingEvent.value = LogInVMRoutingEvent.UNKNOWN_ERROR
            }, {

            }, {

            }
        ).apply { disposables.add(this) }
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
        if (email.value?.trim().isNullOrEmpty()) {
            emailErrorText.value = "Email Field Can't be empty."
            return false
        } else {
            emailErrorText.value = null
            return true
        }
    }

    private fun validatePassword(): Boolean {
        if (password.value?.trim().isNullOrEmpty()) {
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