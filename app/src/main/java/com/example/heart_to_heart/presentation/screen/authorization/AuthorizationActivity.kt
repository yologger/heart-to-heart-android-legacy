package com.example.heart_to_heart.presentation.screen.authorization

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.example.heart_to_heart.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AuthorizationActivity : AppCompatActivity() {

    val router: AuthorizationRouter by inject { parametersOf(this@AuthorizationActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
    }

    override fun onDestroy() {
        Log.d("YOLO", "onDestroy() from AuthorizationActivity")
        super.onDestroy()
    }

    private fun setUpBottomNavigationView() {
    }

    fun hideKeyboard() {
        val view = currentFocus
        if (view == null) {
            return
        } else {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as (InputMethodManager)
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}