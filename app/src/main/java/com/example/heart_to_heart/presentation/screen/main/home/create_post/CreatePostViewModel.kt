package com.example.heart_to_heart.presentation.screen.main.home.create_post

import android.util.Log
import com.example.heart_to_heart.presentation.base.BaseViewModel

class CreatePostViewModel
constructor(

) : BaseViewModel() {

    init {
        Log.d("YOLO", "init{} from CreatePostViewModel")
    }

    override fun onCleared() {
        Log.d("YOLO", "onCleared() from CreatePostViewModel")
        super.onCleared()
    }

    fun test() {
        Log.d("YOLO", "test() from CreatePostViewModel")
    }
}