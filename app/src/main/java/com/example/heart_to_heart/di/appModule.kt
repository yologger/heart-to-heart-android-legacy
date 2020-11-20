package com.example.heart_to_heart.di

import com.example.heart_to_heart.presentation.base.Router
import com.example.heart_to_heart.presentation.screen.AppActivity
import com.example.heart_to_heart.presentation.screen.AppRouter
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

var appModule = module {
    loadKoinModules(module {
        factory { (activity: AppActivity) ->
            AppRouter(activity) as Router
        }
    })
}
