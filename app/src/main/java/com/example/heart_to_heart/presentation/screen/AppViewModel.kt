package com.example.heart_to_heart.presentation.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.GetInitialSessionUseCase
import com.example.heart_to_heart.domain.usecase.GetSessionUseCase
import com.example.heart_to_heart.presentation.base.BaseViewModel

class AppViewModel
constructor(
    private val getSessionUseCase: GetSessionUseCase
): BaseViewModel() {

    val routingEvent: MutableLiveData<AppVMRoutingEvent?> = MutableLiveData(null)


    init {
        Log.d("YOLO", "init() from AppViewModel")
        getSessionUseCase.execute().subscribe { it ->
            Log.d("YOLO", "getSessionUseCase(): ${it}")

            when(it) {
                true -> {
                    routingEvent.postValue(AppVMRoutingEvent.SHOW_MAIN)
                }
                false -> {
                    routingEvent.postValue(AppVMRoutingEvent.SHOW_LOGIN)
                }
            }}.apply { disposables.add(this) }
    }
}