package com.example.heart_to_heart.presentation.screen

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.base.Router

class AppRouter
constructor(
    private val appActivity: AppActivity
) : Router {

    private val navController: NavController by lazy { Navigation.findNavController(appActivity, R.id.activity_app_nhf) }

    override fun showLogIn() {
        Log.d("YOLO", "showLogIn() from AppRouter")
        // Set logInFragment as root of the graph
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
        // Set logInFragment as root of the graph
//        val options = NavOptions.Builder()
//            .setLaunchSingleTop(true)
//            .setPopUpTo(R.id.mainFragment, false)
//            .build()
//        navController.navigate(R.id.mainFragment, null, options)
//        navController.navigate(R.id.mainFragment)
    }

    override fun closeMain() {
        navController.popBackStack()
    }

    override fun showCreatePost() {
//        navController.navigate(R.id.action_mainFragment_to_createPostFragment)
    }

    override fun closeCreatePost() {
        navController.popBackStack()
    }
}