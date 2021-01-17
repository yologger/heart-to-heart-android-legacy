package com.example.heart_to_heart.presentation.screen.authorization.base

import com.example.heart_to_heart.presentation.base.BaseFragment
import com.example.heart_to_heart.presentation.screen.authorization.AuthorizationActivity
import com.example.heart_to_heart.presentation.screen.authorization.AuthorizationRouter


abstract class BaseAuthorizationFragment: BaseFragment() {
    val router: AuthorizationRouter get() = (activity as AuthorizationActivity).router
}