package com.example.heart_to_heart.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.Router
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppActivity : AppCompatActivity() {

    val router: Router by inject { parametersOf(this@AppActivity) }
    private val viewModel: AppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        initBinding()
    }

    private fun initBinding() {
        viewModel.isSessionSet.observe(this, Observer {
            when(it) {
                null -> {}
                true -> {
                    Log.d("YOLO", "showMain() from AppActivity")
                    router.showMain()
                    viewModel.isSessionSet.setValue(null)
                }
                false -> {
                    Log.d("YOLO", "showLogIn()() from AppActivity")
                    router.showLogIn()
                    viewModel.isSessionSet.setValue(null)
                }
            }
        })
    }
}