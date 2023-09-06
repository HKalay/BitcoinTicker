package com.example.bitcointicker.data.coin.coindetail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Description(
    @SerializedName("en")
    val en: String?,

    @SerializedName("tr")
    val tr: String?
) : Parcelable