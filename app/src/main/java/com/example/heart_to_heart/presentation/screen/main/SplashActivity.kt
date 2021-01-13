package com.example.heart_to_heart.presentation.screen.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.screen.AppActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AppActivity::class.java)
            startActivity(intent)
            finish()
        }, 10000)
    }
}