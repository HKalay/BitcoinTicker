package com.example.bitcointicker.core.utils.pref

import android.content.Context

private const val IS_LOGIN = "is_login"

private const val COIN_REFRESH = "coin_refresh"
private const val COIN_REFRESH_IS_ACTIVE = "coin_refresh_is_active"

class SharedPrefManager(context: Context) {

    private val localPrefManager = LocalPrefManager(context)

    fun getIsLogin(): Boolean {
        return localPrefManager.pull(key = IS_LOGIN, false)
    }

    fun setIsLogin(value: Boolean) {
        localPrefManager.push(key = IS_LOGIN, value = value)
    }

    fun getIsCoinRefreshTime(): Int {
        return localPrefManager.pull(key = COIN_REFRESH, 0)
    }

    fun setIsCoinRefreshTime(value: Int) {
        localPrefManager.push(key = COIN_REFRESH, value = value)
    }

    fun getIsCoinRefreshIsActive(): Boolean {
        return localPrefManager.pull(key = COIN_REFRESH_IS_ACTIVE, false)
    }

    fun setIsCoinRefreshIsActive(value: Boolean) {
        localPrefManager.push(key = COIN_REFRESH_IS_ACTIVE, value = value)
    }
}