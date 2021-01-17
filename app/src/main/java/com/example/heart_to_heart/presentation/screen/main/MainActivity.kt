package com.example.heart_to_heart.presentation.screen.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.util.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val router: MainRouter by inject { parametersOf(this@MainActivity) }

    private val viewModel: MainViewModel by viewModel()
    private val postViewModel: PostViewModel by viewModel()

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("YOLO", "onCreate() from MainActivity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setUpBottomNavigationView()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigationView()
    }

    private fun setUpBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.activity_main_bnv)
        val navGraphIds = listOf(R.navigation.home_graph, R.navigation.follow_graph, R.navigation.profile_graph)
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.activity_main_fcv,
            intent = intent
        )
//        controller.observe(this, Observer {  navController ->
//            setupActionBarWithNavController(navController)
//        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("YOLO", "onDestroy() from MainActivity")
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