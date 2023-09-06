package com.example.bitcointicker.data.coin.coindetail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("thumb")
    val thumb: String?,

    @SerializedName("small")
    val small: String?,

    @SerializedName("large")
    val large: String?
) : Parcelable