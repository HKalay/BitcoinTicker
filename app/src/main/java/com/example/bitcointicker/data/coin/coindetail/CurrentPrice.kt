package com.example.bitcointicker.data.coin.coindetail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentPrice(
    @SerializedName("usd")
    val usd: Double?,

    @SerializedName("try")
    val try_: Double?
) : Parcelable