package com.example.heart_to_heart.presentation.screen

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.`interface`.Router
import kotlinx.android.synthetic.main.activity_app.*

class AppRouter
constructor(
    appActivity: AppActivity
) : Router {

    private val navController: NavController by lazy {
        Navigation.findNavController(appActivity, R.id.activity_app_nhf)
    }

    override fun test() {
        Log.d("YOLO", "test() from AppRouter")
    }

    override fun showLogIn() {
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(R.id.logInFragment, false)
            .build()
        navController.navigate(R.id.logInFragment, null, options)
    }

    override fun showSignUp() {
        navController.navigate(R.id.action_logInFragment_to_signUpFragment)
    }

    override fun closeSignUp() {
        navController.popBackStack()
    }

    override fun showMain() {
        Log.d("YOLO", "showMain() from AppRouter")
        navController.navigate(R.id.mainFragment)
    }

    override fun closeMain() {
        navController.popBackStack()
    }
}