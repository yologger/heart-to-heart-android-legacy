package com.example.heart_to_heart.infrastructure.localDB

import android.content.Context
import android.content.SharedPreferences
import com.example.heart_to_heart.data.model.Session
import com.example.heart_to_heart.data.model.Tokens
import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.rxkotlin.cast
import io.reactivex.subjects.BehaviorSubject

class DefaultSessionStorage
constructor(
    private val context: Context
) : SessionStorage {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
    private var _session: Session? = null
    private var sessionState: BehaviorSubject<Boolean>

    init {
        loadSession()
        sessionState = if (_session == null) {
            BehaviorSubject.createDefault<Boolean>(false)
        } else {
            BehaviorSubject.createDefault<Boolean>(true)
        }
    }

    private fun loadSession() {
        val json = prefs.getString("session_key", null).toString()
        val gson = Gson()
        _session = gson.fromJson(json, Session::class.java)
    }

    override fun getSessionState(): Observable<Boolean> = sessionState.cast()

    override fun setSession(session: Session) {
        _session = session
        val gson = Gson()
        val json = gson.toJson(session)
        prefs.edit().putString("session_key", json).apply()
    }

    override fun removeSession() {
        prefs.edit().remove("session_key").apply()
        _session = null
        sessionState.onNext(false)
    }

    override fun updateTokens(tokens: Tokens) {
        var session = Session(
            email = _session?.email!!,
            profile = _session?.profile!!,
            tokens = tokens
        )
        val gson = Gson()
        val json = gson.toJson(session)
        prefs.edit().putString("session_key", json).apply()
        _session = session
    }

    override fun getAccessToken(): String? = _session?.tokens?.accessToken
    override fun getRefreshToken(): String? = _session?.tokens?.refreshToken
    override fun getSession(): Session? = _session
}