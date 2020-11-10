package com.example.heart_to_heart.presentation.base

import androidx.fragment.app.Fragment
import com.example.heart_to_heart.presentation.`interface`.Router
import com.example.heart_to_heart.presentation.screen.AppActivity

abstract class BaseFragment: Fragment() {
    val router: Router get() = (activity as AppActivity).router
}