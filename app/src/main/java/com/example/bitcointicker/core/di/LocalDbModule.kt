package com.example.bitcointicker.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.bitcointicker.app.db.AppDatabase
import com.example.bitcointicker.app.db.dao.CoinDAO

@Module
@InstallIn(SingletonComponent::class)
object LocalDbModule {

    @Provides
    fun provideDb(@ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java, "crypt_db"
        ).build()
    }

    @Provides
    fun provideCoinDao(db: AppDatabase): CoinDAO {
        return db.coinDao()
    }
}
