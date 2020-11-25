package com.example.heart_to_heart.presentation.screen

import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.GetSessionUseCase
import com.example.heart_to_heart.presentation.base.BaseViewModel

class AppViewModel
constructor(
    private val getSessionUseCase: GetSessionUseCase
): BaseViewModel() {

    val routingEvent: MutableLiveData<AppVMRoutingEvent?> = MutableLiveData(null)

    init {
        getSessionUseCase.execute().subscribe {
            when(it) {
                false -> { routingEvent.postValue(AppVMRoutingEvent.SHOW_LOGIN) }
                true -> { routingEvent.postValue(AppVMRoutingEvent.SHOW_MAIN) }
            }}.apply { disposables.add(this) }
    }
}