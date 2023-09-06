package com.example.bitcointicker.data.request

import com.example.bitcointicker.data.coin.CoinListResponse
import retrofit2.http.GET

interface NetworkService {

    @GET("coins/list")
    suspend fun getCoinList(): CoinListResponse
}