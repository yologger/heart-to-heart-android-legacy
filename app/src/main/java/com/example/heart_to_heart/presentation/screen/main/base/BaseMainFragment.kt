package com.example.heart_to_heart.presentation.screen.main.base

import com.example.heart_to_heart.presentation.base.BaseFragment
import com.example.heart_to_heart.presentation.screen.main.MainActivity
import com.example.heart_to_heart.presentation.screen.main.MainRouter

abstract class BaseMainFragment: BaseFragment() {
    val router: MainRouter get() = (activity as MainActivity).router
}