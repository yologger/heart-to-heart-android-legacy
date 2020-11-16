package com.example.heart_to_heart.data.repository.dataSource.local

import com.example.heart_to_heart.data.model.Session
import com.example.heart_to_heart.data.model.Tokens
import com.example.heart_to_heart.data.repository.dataSource.local.base.LocalDataSource

interface SessionStorage: LocalDataSource {
    fun setSession(session: Session)
    fun getSession(): Session?
    fun removeSession()

    fun saveToken(tokens: Tokens)
    fun getTokens(): Tokens?
    fun printToken()
}