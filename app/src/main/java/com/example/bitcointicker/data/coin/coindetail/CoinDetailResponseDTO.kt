package com.example.bitcointicker.data.coin.coindetail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinDetailResponseDTO(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("market_data")
    val market_data: MarketData?,

    @SerializedName("image")
    val image: Image?,

    @SerializedName("description")
    val description: Description?,

    @SerializedName("hashing_algorithm")
    val hashing_algorithm: String?
): Parcelable
