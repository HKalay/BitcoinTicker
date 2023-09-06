package com.example.bitcointicker.core.netowrk.interceptor

import android.content.Context
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response

class ResponseControlInterceptor(
    var gson: Gson,
    var context: Context
) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        //TODO dönen response hataları burada handle edilebilir
        return response
    }
}