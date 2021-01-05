package com.example.heart_to_heart.presentation.screen.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.presentation.base.BaseViewModel

class MainViewModel
constructor(

) : BaseViewModel() {

    val routingEvent = MutableLiveData<MainVMRoutingEvent>(MainVMRoutingEvent.SHOW_MAIN)

    init {
        validateSession()
    }


    private fun validateSession() {
    }
}