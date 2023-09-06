package com.example.bitcointicker.data.request

import com.example.bitcointicker.data.coin.CoinResponseDTO
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {

    @GET("coins/list")
    suspend fun getCoinList(): List<CoinResponseDTO>

    @GET("coins/{id}")
    suspend fun getCoinDetail(
        @Path("id") id: String
    ): CoinDetailResponseDTO
}