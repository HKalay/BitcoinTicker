package com.example.bitcointicker.data.coin.coindetail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarketData(
    @SerializedName("current_price")
    val current_price: CurrentPrice?,

    @SerializedName("price_change_percentage_24h")
    val price_change_percentage_24h: Double?
) : Parcelable