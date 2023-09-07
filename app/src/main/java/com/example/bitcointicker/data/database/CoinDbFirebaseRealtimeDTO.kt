package com.example.bitcointicker.data.database

import android.os.Parcelable
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CoinDbFirebaseRealtimeDTO(
    var coinId: String,
    var coinDetailResponseDTO: CoinDetailResponseDTO
): Parcelable

