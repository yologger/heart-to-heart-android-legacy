package com.example.heart_to_heart.presentation.screen.authorization

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.heart_to_heart.R
import com.example.heart_to_heart.presentation.screen.main.MainActivity

class AuthorizationRouter
constructor(
    private val authorizationActivity: AuthorizationActivity
) {

    private val navController: NavController by lazy { Navigation.findNavController(authorizationActivity, R.id.activity_authorization_nhf) }

    fun openSignUp() {
        navController.navigate(R.id.action_logInFragment_to_signUpFragment)
    }

    fun closeSignUp() {
        navController.popBackStack()
    }

    fun openMain() {
        val intent = Intent(authorizationActivity, MainActivity::class.java)
        authorizationActivity.startActivity(intent)
        authorizationActivity.finish()
    }
}