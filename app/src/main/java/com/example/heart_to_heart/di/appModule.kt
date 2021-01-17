package com.example.heart_to_heart.di

import com.example.heart_to_heart.presentation.screen.authorization.AuthorizationActivity
import com.example.heart_to_heart.presentation.screen.authorization.AuthorizationRouter
import com.example.heart_to_heart.presentation.screen.main.MainActivity
import com.example.heart_to_heart.presentation.screen.main.MainRouter
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

var appModule = module {
    loadKoinModules(module {
        // factory { (activity: AppActivity) -> AppRouter(activity) as Router }
        factory { (activity: AuthorizationActivity) -> AuthorizationRouter(activity) }
        factory { (activity: MainActivity) -> MainRouter(activity) }
    })
}
