package com.example.heart_to_heart.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.`interface`.Router
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AppActivity : AppCompatActivity() {

    val router: Router by inject { parametersOf(this@AppActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        this.router.test()
    }
}