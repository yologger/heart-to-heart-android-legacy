package com.example.heart_to_heart.data.repository.dataSource.local

import com.example.heart_to_heart.data.model.Session
import com.example.heart_to_heart.data.model.Tokens
import com.example.heart_to_heart.data.repository.dataSource.local.base.LocalDataSource
import io.reactivex.Observable

interface SessionStorage: LocalDataSource {
    fun getSessionState(): Observable<Boolean>
    fun setSession(session: Session)
    fun removeSession()
    fun updateTokens(tokens: Tokens)
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun getSession(): Session?
    fun getUserId(): String?
}