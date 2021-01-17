package com.example.heart_to_heart.presentation.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.screen.authorization.AuthorizationActivity
import com.example.heart_to_heart.presentation.screen.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initUI()
        initBinding()
        validateSession()
    }

    private fun initUI() {}

    fun initBinding() {
        viewModel.routingEvent.observe(this, Observer {
            when (it) {
                null -> {
                }
                SplashVMRoutingEvent.LOGGED_IN -> {
                    navigateToMain()
                    Log.d("YOLO", "LOGGED IN")
                    // navigateToMain(true)
                    viewModel.routingEvent.setValue(null)
                }
                SplashVMRoutingEvent.NOT_LOGGED_IN -> {
                    Log.d("YOLO", "NOT LOGGED IN")
                    navigateToAuthorization()
                    // navigateToMain(false)
                    viewModel.routingEvent.setValue(null)
                }
                SplashVMRoutingEvent.NETWORK_ERROR -> {
                    var toast = Toast.makeText(this, "SERVER CONNECTION ERROR", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    finish()
                }
            }
        })
    }

    private fun validateSession() = viewModel.validateSession()

    private fun navigateToAuthorization() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
        finish()
//        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this, AppActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 10000)
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}