package com.example.heart_to_heart.presentation.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel
constructor(

) : ViewModel() {
    fun test() {
        Log.d("YOLO", "test() from MainViewModel")
    }
}