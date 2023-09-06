package com.example.bitcointicker.core.utils.pref

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class LocalPrefManager(context: Context) {

    private var shared: SharedPreferences? = null

    init {
        this.shared = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun push(key: String, value: String) {
        shared?.edit()?.putString(key, value)?.apply()
    }

    fun push(key: String, value: Int) {
        shared?.edit()?.putInt(key, value)?.apply()
    }

    fun push(key: String, value: Long) {
        shared?.edit()?.putLong(key, value)?.apply()
    }

    fun push(key: String, value: Boolean) {
        shared?.edit()?.putBoolean(key, value)?.apply()
    }

    fun push(key: String, value: Float) {
        shared?.edit()?.putFloat(key, value)?.apply()
    }

    fun <T> push(key: String, obj: T) {
        val inString = Gson().toJson(obj)
        shared?.edit()?.putString(key, inString)?.commit()
    }

    fun <T> pull(key: String, obj: Class<T>): T? {
        val value = shared?.getString(key, null)
        return if (value != null) {
            Gson().fromJson(value, obj)
        } else {
            null
        }
    }

    fun pull(key: String, defaultValue: String): String =
        shared?.getString(key, defaultValue)!!

    fun pull(key: String, defaultValue: Int): Int =
        shared?.getInt(key, defaultValue)!!

    fun pull(key: String, defaultValue: Long): Long =
        shared?.getLong(key, defaultValue)!!

    fun pull(key: String, defaultValue: Float): Float =
        shared?.getFloat(key, defaultValue)!!

    fun pull(key: String, defaultValue: Boolean): Boolean =
        shared?.getBoolean(key, defaultValue)!!

    fun getAllKeys(): List<String> {
        val keyList = arrayListOf<String>()
        val allEntries = shared?.all
        allEntries?.forEach {
            keyList.add(it.key)
        }
        return keyList
    }

    fun remove(key: String) {
        shared?.edit()?.remove(key)?.apply()
    }
}