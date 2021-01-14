package com.example.heart_to_heart.presentation.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
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
        val isLoggedIn = intent.extras?.getBoolean("isLoggedIn")!!
        initStartDestination(isLoggedIn)
        initBinding()
    }

    private fun initStartDestination(isLoggedIn: Boolean) {
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.activity_app_nhf)) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.app_nav)
        var destination = if (isLoggedIn) R.id.mainFragment else R.id.logInFragment
        graph.startDestination = destination
        navHostFragment.navController.graph = graph
    }

    private fun initBinding() {
        viewModel.routingEvent.observe(this, Observer { event ->
            when (event) {
                null -> { }
                AppVMRoutingEvent.SHOW_LOGIN -> {
                    router.showLogIn()
                    viewModel.routingEvent.postValue(null)
                }
                AppVMRoutingEvent.SHOW_MAIN -> {
                    router.showMain()
                    viewModel.routingEvent.postValue(null)
                }
            }
        })
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