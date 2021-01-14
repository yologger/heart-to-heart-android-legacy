package com.example.heart_to_heart.presentation.screen

import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.ValidateSessionUseCase
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.ValidateSessionError
import com.example.heart_to_heart.infrastructure.network.authoriztion_api.model.ValidateSessionResult
import com.example.heart_to_heart.presentation.base.BaseViewModel

class SplashViewModel
constructor(
    private val validateSessionUseCase: ValidateSessionUseCase
) : BaseViewModel() {

    val routingEvent: MutableLiveData<SplashVMRoutingEvent?> = MutableLiveData(null)

    fun validateSession() {
        validateSessionUseCase.execute().subscribe {
            when(it) {
                is ValidateSessionResult.SUCCESS -> {
                    routingEvent.value = SplashVMRoutingEvent.LOGGED_IN
                }
                is ValidateSessionResult.FAILURE -> {
                    when(it.error) {
                        ValidateSessionError.EXPIRED_ACCESS_TOKEN -> {
                            routingEvent.value = SplashVMRoutingEvent.NOT_LOGGED_IN
                        }
                        ValidateSessionError.EXPIRED_REFRESH_TOKEN -> {
                            routingEvent.value = SplashVMRoutingEvent.NOT_LOGGED_IN
                        }
                        ValidateSessionError.NETWORK_ERROR -> {
                            routingEvent.value = SplashVMRoutingEvent.NETWORK_ERROR
                        }
                    }
                }
            }
        }.apply { disposables.add(this) }
    }
}