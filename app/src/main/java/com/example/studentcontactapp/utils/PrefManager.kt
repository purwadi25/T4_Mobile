package com.example.studentcontactapp.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "StudentPortalPrefs"
        private const val KEY_LOGIN = "is_login"
        private const val KEY_USERNAME = "username"
        private const val KEY_REMEMBER = "remember"
    }

    fun saveSession(username: String, remember: Boolean) {
        preferences.edit().apply {
            putBoolean(KEY_LOGIN, true)
            putString(KEY_USERNAME, username)
            putBoolean(KEY_REMEMBER, remember)
            apply()
        }
    }

    fun isLogin(): Boolean {
        return preferences.getBoolean(KEY_LOGIN, false)
    }

    fun isRememberChecked(): Boolean {
        return preferences.getBoolean(KEY_REMEMBER, false)
    }

    fun getUsername(): String {
        return preferences.getString(KEY_USERNAME, "") ?: ""
    }

    fun logout() {
        preferences.edit().clear().apply()
    }
}