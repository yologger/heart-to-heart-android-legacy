package com.example.heart_to_heart.presentation.screen.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.heart_to_heart.presentation.base.BaseFragment

class HomeViewModel
constructor(

) : ViewModel() {
    init {
        Log.d("YOLO", "init{} from HomeViewModel")
    }

    fun test() {
        Log.d("YOLO", "test() from HomeViewModel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("YOLO", "onCleared() from HomeViewModel")
    }
}