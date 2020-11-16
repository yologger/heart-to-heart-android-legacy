package com.example.heart_to_heart.di

import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.example.heart_to_heart.infrastructure.localDB.DefaultSessionStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

var infrastructureModule = module {
    single<SessionStorage> { DefaultSessionStorage(androidContext()) }
}