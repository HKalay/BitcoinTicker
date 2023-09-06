package com.example.bitcointicker.core.utils.pref

import android.content.Context

private const val IS_LOGIN = "is_login"

class SharedPrefManager(context: Context) {

    private val localPrefManager = LocalPrefManager(context)

    fun getIsLogin(): Boolean {
        return localPrefManager.pull(key = IS_LOGIN, false)
    }

    fun setIsLogin(value: Boolean) {
        localPrefManager.push(key = IS_LOGIN, value = value)
    }
}