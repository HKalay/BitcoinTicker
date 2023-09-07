package com.example.bitcointicker.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import com.example.bitcointicker.data.coin.CoinResponseDTO
import com.example.bitcointicker.data.coin.coindetail.CoinDetailResponseDTO


@Parcelize
data class CoinDbFirebaseRealtimeDTO(
    var coinId: String,
    var coinDetailResponseDTO: CoinDetailResponseDTO
): Parcelable

