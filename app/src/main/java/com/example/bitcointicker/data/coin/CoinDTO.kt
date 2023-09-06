package com.example.bitcointicker.data.coin

import com.google.gson.annotations.SerializedName

data class CoinDTO(
    @SerializedName("id")
    var id: String,
    @SerializedName("symbol")
    var symbol: String,
    @SerializedName("name")
    var name: String
)
