package com.example.heart_to_heart.data.repository

import android.util.Log
import com.example.heart_to_heart.domain.`interface`.AuthorizationRepository

class DefaultAuthorizationRepository
constructor(

) : AuthorizationRepository {
    override fun test() {
        Log.d("YOLO", "test() from DefaultAuthorizationRepository")
    }
}