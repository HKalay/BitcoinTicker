package com.example.bitcointicker.app.db.dao

import androidx.room.*
import com.example.bitcointicker.data.database.CoinDbDTO
@Dao
interface CoinDAO {
    @Query("SELECT * FROM saved_coins")
    fun getAllCoins(): List<CoinDbDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoin(coinDbDTO: CoinDbDTO)

    @Query("DELETE FROM saved_coins WHERE id = :id")
    fun deleteCoin(id:Int)

    @Query("SELECT * FROM saved_coins WHERE id= :id")
    fun getCoinWithId(id: String): CoinDbDTO
}