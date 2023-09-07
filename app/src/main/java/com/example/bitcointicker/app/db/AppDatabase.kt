package com.example.bitcointicker.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bitcointicker.app.db.dao.CoinDAO
import com.example.bitcointicker.data.database.CoinDbRoomDTO

@Database(
    entities = [CoinDbRoomDTO::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDAO

}