package com.example.heart_to_heart.presentation.screen.main.profile

import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.LogOutUseCase
import com.example.heart_to_heart.presentation.base.BaseViewModel

class ProfileViewModel
constructor(
    private val logOutUseCase: LogOutUseCase
) : BaseViewModel() {

    val routingEvent: MutableLiveData<ProfileVMRoutingEvent?> = MutableLiveData(null)

    fun logOut() {
        logOutUseCase.execute().subscribe({
            if(it) {
                routingEvent.setValue(ProfileVMRoutingEvent.SHOW_LOGIN)
                routingEvent.setValue(null)
            } else {
                routingEvent.setValue(ProfileVMRoutingEvent.SHOW_LOGIN)
                routingEvent.setValue(null)
            }
        }, {

        }, {

        }).apply { disposables.add(this) }
    }
}