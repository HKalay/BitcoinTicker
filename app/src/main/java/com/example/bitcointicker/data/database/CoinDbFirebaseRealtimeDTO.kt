package com.example.bitcointicker.data.database

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CoinDbFirebaseRealtimeDTO(
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("symbol")
    var symbol: String
) : Parcelable

