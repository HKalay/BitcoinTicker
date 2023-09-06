package com.example.bitcointicker.core.utils.pref

import android.content.Context

class SharedPrefManager(context: Context) {

    private val localPrefManager = LocalPrefManager(context)

    fun getDefaultData(): String {
        return localPrefManager.pull(key = "default_data", "true")
    }

    fun setDefaultData(value: String) {
        localPrefManager.push(key = "default_data", value = value)
    }
}