package com.example.bitcointicker.core.utils.pref

import android.content.Context

private const val COIN_REFRESH_TIME = "coin_refresh"
private const val COIN_REFRESH_IS_ACTIVE = "coin_refresh_is_active"

private const val EMAIL = "email"
private const val PASSWORD = "password"

class SharedPrefManager(context: Context) {

    private val localPrefManager = LocalPrefManager(context)

    fun getIsCoinRenewalFrequencyTime(coindId: String): Int {
        return localPrefManager.pull(key = COIN_REFRESH_TIME + "_" + coindId, 5)
    }

    fun setIsCoinRenewalFrequencyTime(coindId: String, value: Int) {
        localPrefManager.push(key = COIN_REFRESH_TIME + "_" + coindId, value = value)
    }

    fun getIsCoinRefreshIsActive(coindId: String): Boolean {
        return localPrefManager.pull(key = COIN_REFRESH_IS_ACTIVE + "_" + coindId, false)
    }

    fun setIsCoinRefreshIsActive(coindId: String, value: Boolean) {
        localPrefManager.push(key = COIN_REFRESH_IS_ACTIVE + "_" + coindId, value = value)
    }

    fun getEmail(): String {
        return localPrefManager.pull(key = EMAIL, "")
    }

    fun setIsEmail(email: String) {
        localPrefManager.push(key = EMAIL, value = email)
    }

    fun getPassword(): String {
        return localPrefManager.pull(key = PASSWORD, "")
    }

    fun setIsPassword(password: String) {
        localPrefManager.push(key = PASSWORD, value = password)
    }
}