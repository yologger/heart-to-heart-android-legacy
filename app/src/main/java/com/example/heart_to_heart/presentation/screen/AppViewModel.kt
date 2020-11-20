package com.example.heart_to_heart.presentation.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.heart_to_heart.domain.usecase.GetSessionUseCase
import com.example.heart_to_heart.presentation.base.BaseViewModel

class AppViewModel
constructor(
    private val getSessionUseCase: GetSessionUseCase
): BaseViewModel() {

    var isSessionSet: MutableLiveData<Boolean?> = MutableLiveData(null)

    init {
        getSessionUseCase.execute().subscribe({
            isSessionSet.setValue(it)
        }, {
            isSessionSet.setValue(null)
        }, {

        }, {

        }).apply { disposables.add(this) }
    }
}