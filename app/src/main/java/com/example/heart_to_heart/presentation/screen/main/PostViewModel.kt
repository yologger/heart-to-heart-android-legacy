package com.example.heart_to_heart.presentation.screen.main

import android.util.Log
import com.example.heart_to_heart.presentation.base.BaseViewModel

class PostViewModel
constructor(

) : BaseViewModel() {

    var data = 0
    fun test() {
        data += 1
        Log.d("YOLO", "test() from PostViewModel: ${data}")
    }
}