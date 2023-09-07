package com.example.bitcointicker.data.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CoinDbFirebaseRealtimeDTO(
    var id: String,
    var symbol: String,
    var name: String
) : Parcelable

