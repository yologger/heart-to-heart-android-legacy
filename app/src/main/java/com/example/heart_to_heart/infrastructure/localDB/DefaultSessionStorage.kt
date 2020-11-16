package com.example.heart_to_heart.infrastructure.localDB

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.heart_to_heart.data.model.Session
import com.example.heart_to_heart.data.model.Tokens
import com.example.heart_to_heart.data.repository.dataSource.local.SessionStorage
import com.google.gson.Gson

class DefaultSessionStorage
constructor(
    private val context: Context
) : SessionStorage {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_info", Context.MODE_PRIVATE)

    override fun setSession(session: Session) {
        val gson = Gson()
        val json = gson.toJson(session)
        prefs.edit().putString("session_key", json).apply()
    }

    override fun getSession(): Session? {
        val json = prefs.getString("session_key", null).toString()
        val gson = Gson()
        return gson.fromJson(json, Session::class.java)
    }

    override fun removeSession() {
        prefs.edit().remove("session_key").apply()
    }



    override fun saveToken(tokens: Tokens) {
        val gson = Gson()
        val json = gson.toJson(tokens)
        prefs.edit().putString("tokens", json).apply()
    }

    override fun getTokens(): Tokens? {
        val json = prefs.getString("tokens", null).toString()
        val gson = Gson()
        val value = gson.fromJson<Tokens>(json, Tokens::class.java)
        return value
    }


    override fun printToken() {
        var name = get("name", "ronaldo")
        Log.d("YOLO", "name: ${name}")
    }
//    private fun set(key: String, value: String) {
//        prefs.edit().putString(key, value).apply()
//    }

    private fun <T> set(key: String, value: T) {
        val gson = Gson()
        val json = gson.toJson(value)
        prefs.edit().putString(key, json).apply()
    }
//    private fun<T> get (key: String, defaultValue: String) {
//        val json = prefs.getString(key, defaultValue).toString()
//        val gson = Gson()
//        val value = gson.fromJson<T>(json, T::class.java)
//    }

    private fun get(key: String, defaultValue: String) {
        val json = prefs.getString(key, defaultValue).toString()
        val gson = Gson()
        val value = gson.fromJson<Session>(json, Session::class.java)
    }

//    private fun get(key: String, defaultValue: String): String {
//        return prefs.getString(key, defaultValue).toString()
//    }

    private fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    private fun clearAll() {
        prefs.edit().clear().apply()
    }
}