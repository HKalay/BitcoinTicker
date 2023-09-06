package com.example.bitcointicker.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import com.example.bitcointicker.data.coin.CoinDTO


@Parcelize
@Entity(tableName = "saved_coins", indices = [Index(value = ["coin_id"], unique = true)])
data class CoinDbDTO(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "coin_id")
    var coinId: String,

    @ColumnInfo(name = "coin_dto")
    var instagramPostUIModel: CoinDTO
): Parcelable

