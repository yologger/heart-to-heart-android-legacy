package com.example.heart_to_heart.presentation.screen.main

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.screen.authorization.AuthorizationActivity

class MainRouter
constructor(
    private val mainActivity: MainActivity
) {
    private val navController: NavController by lazy { Navigation.findNavController(mainActivity, R.id.activity_main_fcv) }

    fun openCreatePost() = navController.navigate(R.id.action_homeFragment_to_createPostFragment)
    fun closeCreatePost() = navController.popBackStack()
    fun openLogIn() {
        val intent = Intent(mainActivity, AuthorizationActivity::class.java)
        mainActivity.startActivity(intent)
        mainActivity.finish()
    }
}