package com.example.heart_to_heart.presentation.base

interface Router {
    fun showLogIn()
    fun showSignUp()
    fun closeSignUp()
    fun showMain()
    fun closeMain()
    fun showCreatePost()
    fun closeCreatePost()
//    fun back()
//    fun navigationUp(): Boolean
//    fun isInRootScreen(): Boolean
}