package com.example.heart_to_heart.presentation.screen.authorization.signup

import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.SignUpUseCase
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.SignUpError
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.SignUpResult
import com.example.heart_to_heart.presentation.base.BaseViewModel

class SignUpViewModel
constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {

    var routingEvent: MutableLiveData<SignUpVMRoutingEvent?> =
        MutableLiveData<SignUpVMRoutingEvent?>(null)

    val email: MutableLiveData<String> = MutableLiveData("ronaldo@gmail.com")
    var firstName: MutableLiveData<String> = MutableLiveData("Cristiano")
    var lastName: MutableLiveData<String> = MutableLiveData("Ronaldo")
    var nickname: MutableLiveData<String> = MutableLiveData("CR7")
    val password: MutableLiveData<String> = MutableLiveData("12345")

//    val email: MutableLiveData<String> = MutableLiveData("")
//    var firstName: MutableLiveData<String> = MutableLiveData("")
//    var lastName: MutableLiveData<String> = MutableLiveData("")
//    var nickname: MutableLiveData<String> = MutableLiveData("")
//    val password: MutableLiveData<String> = MutableLiveData("")

    val emailErrorText: MutableLiveData<String?> = MutableLiveData("")
    var firstNameErrorText: MutableLiveData<String?> = MutableLiveData("")
    var lastNameErrorText: MutableLiveData<String?> = MutableLiveData("")
    var nicknameErrorText: MutableLiveData<String?> = MutableLiveData("")
    var passwordErrorText: MutableLiveData<String?> = MutableLiveData("")

    fun logIn() {
        routingEvent.value = SignUpVMRoutingEvent.CLOSE
    }

    fun signUp() {
        if (!validateInputs()) {
            return
        }
        signUpUseCase.email = email.value ?: ""
        signUpUseCase.firstName = firstName.value ?: ""
        signUpUseCase.lastName = lastName.value ?: ""
        signUpUseCase.nickname = nickname.value ?: ""
        signUpUseCase.password = password.value ?: ""
        signUpUseCase.execute().subscribe({ signUpResult ->
            when (signUpResult) {
                is SignUpResult.SUCCESS -> {
                    routingEvent.value = SignUpVMRoutingEvent.CLOSE
                }
                is SignUpResult.FAILURE -> {
                    when (signUpResult.error) {
                        SignUpError.ALREADY_EXISTED_EMAIL -> {
                            routingEvent.value = SignUpVMRoutingEvent.SHOW_ALREADY_SIGNED_UP_ERROR
                        }
                        SignUpError.NETWORK_CONNECTION_ERROR -> {
                            routingEvent.value = SignUpVMRoutingEvent.SHOW_NETWORK_ERROR
                        }
                        SignUpError.UNKNOWN_ERROR -> {
                            routingEvent.value = SignUpVMRoutingEvent.SHOW_UNKNOWN_ERROR
                        }
                    }
                }
            }
        }, {
            routingEvent.value = SignUpVMRoutingEvent.SHOW_UNKNOWN_ERROR
        }, {

        }).apply { disposables.add(this) }
    }

    private fun validateInputs(): Boolean {
        if (!validateEmail() or !validateFirstName() or !validateLastName() or !validateNickname() or !validatePassword()) {
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

    private fun validateFirstName(): Boolean {
        if (firstName.value?.trim().isNullOrEmpty()) {
            firstNameErrorText.value = "Password Field Can't be empty."
            return false
        } else {
            firstNameErrorText.value = null
            return true
        }
    }

    private fun validateLastName(): Boolean {
        if (lastName.value?.trim().isNullOrEmpty()) {
            lastNameErrorText.value = "Password Field Can't be empty."
            return false
        } else {
            lastNameErrorText.value = null
            return true
        }
    }

    private fun validateNickname(): Boolean {
        if (nickname.value?.trim().isNullOrEmpty()) {
            nicknameErrorText.value = "Password Field Can't be empty."
            return false
        } else {
            nicknameErrorText.value = null
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
}

