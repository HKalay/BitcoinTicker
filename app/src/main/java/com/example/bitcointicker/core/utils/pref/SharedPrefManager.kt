package com.example.bitcointicker.core.utils.pref

import android.content.Context

private const val IS_LOGIN = "is_login"

private const val COIN_REFRESH_TIME = "coin_refresh"
private const val COIN_REFRESH_IS_ACTIVE = "coin_refresh_is_active"

class SharedPrefManager(context: Context) {

    private val localPrefManager = LocalPrefManager(context)

    fun getIsLogin(): Boolean {
        return localPrefManager.pull(key = IS_LOGIN, false)
    }

    fun setIsLogin(value: Boolean) {
        localPrefManager.push(key = IS_LOGIN, value = value)
    }

    fun getIsCoinRefreshTime(coindId: String): Int {
        return localPrefManager.pull(key = COIN_REFRESH_TIME + "_" + coindId, 0)
    }

    fun setIsCoinRefreshTime(coindId: String, value: Int) {
        localPrefManager.push(key = COIN_REFRESH_TIME + "_" + coindId, value = value)
    }

    fun getIsCoinRefreshIsActive(coindId: String): Boolean {
        return localPrefManager.pull(key = COIN_REFRESH_IS_ACTIVE + "_" + coindId, false)
    }

    fun setIsCoinRefreshIsActive(coindId: String, value: Boolean) {
        localPrefManager.push(key = COIN_REFRESH_IS_ACTIVE + "_" + coindId, value = value)
    }
}