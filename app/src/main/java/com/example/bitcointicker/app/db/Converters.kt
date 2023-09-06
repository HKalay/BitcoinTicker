package com.example.bitcointicker.app.db

import androidx.room.TypeConverter
import com.example.bitcointicker.data.coin.CoinResponseDTO
import com.google.gson.Gson

object Converters {

    @TypeConverter
    fun fromJson(jsonString: String): CoinResponseDTO {
        return Gson().fromJson(jsonString, CoinResponseDTO::class.java)
    }

    @TypeConverter
    fun toJson(coinResponseDTO: CoinResponseDTO): String {
        return Gson().toJson(coinResponseDTO)
    }
}