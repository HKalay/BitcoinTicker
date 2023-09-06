package com.example.bitcointicker.data.coin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinResponseDTO(
    @SerializedName("id")
    var id: String,
    @SerializedName("symbol")
    var symbol: String,
    @SerializedName("name")
    var name: String
):Parcelable
