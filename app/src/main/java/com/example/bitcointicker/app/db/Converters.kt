package com.example.bitcointicker.app.db

import androidx.room.TypeConverter
import com.example.bitcointicker.data.coin.CoinDTO
import com.google.gson.Gson

object Converters {

    @TypeConverter
    fun fromJson(jsonString: String): CoinDTO {
        return Gson().fromJson(jsonString, CoinDTO::class.java)
    }

    @TypeConverter
    fun toJson(coinDTO: CoinDTO): String {
        return Gson().toJson(coinDTO)
    }
}