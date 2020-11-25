package com.example.heart_to_heart.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        viewModel.routingEvent.observe(this, Observer { event ->
            when (event) {
                AppVMRoutingEvent.SHOW_LOGIN -> {
                    router.showLogIn()
                    viewModel.routingEvent.postValue(null)
                }
                AppVMRoutingEvent.SHOW_MAIN -> {
                    router.showMain()
                    viewModel.routingEvent.postValue(null)
                }
                else -> { }
            }
        })
    }
}